package view;

import app.GameMainApplication;
import app.JsonApplication;
import app.RankingApplication;
import app.RestartGameController;
import interface_adapters.NavigationManagerJson;
import interface_adapters.nevagateallowcatepage.NevagateAllowcateController;
import interface_adapters.rankinglist.RankingInterface;
import interface_adapters.rankinglist.RankingController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Main view.
 */

public class MainView extends JFrame {
    private JButton newGameButton = new JButton("New Game");
    private JButton rankingButton = new JButton("Ranking List");
    private JButton quitButton = new JButton("Quit");
    private JButton logoutButton = new JButton("Log Out");

    private NevagateAllowcateController nevagateAllowcateController;
    private NavigationManagerJson navigationManager;
    private RestartGameController restartGameController;

    public MainView() {
        // Set layout and container
        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Title
        final JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, Constants.THIRTY));
        container.add(titleLabel);

        // Set button fonts
        newGameButton.setFont(new Font("Arial", Font.BOLD, Constants.TWENTY));
        rankingButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        quitButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));

        newGameButton.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.FIFTY));
        rankingButton.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.FIFTY));
        quitButton.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.FORTY));
        logoutButton.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.FIFTY));

        newGameButton.setBackground(Constants.THEME_COLOR);
        rankingButton.setBackground(Color.WHITE);
        quitButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.WHITE);

        newGameButton.setForeground(Color.WHITE);
        rankingButton.setForeground(Constants.THEME_COLOR);
        quitButton.setForeground(Constants.THEME_COLOR);
        logoutButton.setForeground(Constants.THEME_COLOR);

        // Add buttons to the container
        container.add(newGameButton);
        container.add(rankingButton);
        container.add(quitButton);
        container.add(logoutButton);

        // Layout constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, Constants.THIRTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, Constants.FORTY, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rankingButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, rankingButton, Constants.TEN, SpringLayout.SOUTH, newGameButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logoutButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, logoutButton, Constants.TEN, SpringLayout.SOUTH, rankingButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quitButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, quitButton, Constants.TEN, SpringLayout.SOUTH, logoutButton);

        // Add listeners for buttons
        addListeners();

        // Set window properties (moved to render())
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FIVE_HUNDRED, Constants.THREE_HUNDRED);
    }

    public void setNevagateAllowcateController(NevagateAllowcateController nevagateAllowcateController) {
        System.out.println("MainView instance in setNevagateAllowcateController: " + System.identityHashCode(this));
        this.nevagateAllowcateController = nevagateAllowcateController;
    }

    public void setRestartGameController(RestartGameController restartGameController) {
        this.restartGameController = restartGameController;
    }

    // Add ActionListener to buttons
    private void addListeners() {
        // Switch to GameView when "New Game" is clicked
        newGameButton.addActionListener(e -> {
            restartGameController.resetGame();
            if (nevagateAllowcateController != null) {
                nevagateAllowcateController.execute();
            }
            else {
                JOptionPane.showMessageDialog(this, "Navigation controller not initialized.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Switch to RankView when "Ranking" is clicked
        rankingButton.addActionListener(e -> {
            if (navigationManager != null) {
                navigationManager.showRankingView();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Navigation Manager is not initialized. Cannot navigate to Ranking View.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Exit the game when "Quit" is clicked
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        // Switch to LoginView when "Logout" is clicked
        logoutButton.addActionListener(e -> {
            if (navigationManager != null) {
                navigationManager.showLoginView();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Error navigating to LoginView.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Sets the NavigationManager for this view.
     *
     * @param navigationManager The NavigationManager instance.
     */
    public void setNavigationManager(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
    }

    // Add render method
    public void render() {
        setVisible(true);
    }

    public void disrender() {
        setVisible(false);
    }

    public static void main(String[] args) {
        try {
            new JsonApplication("PlayerFile", "PlayerFile", "RankingFile");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
