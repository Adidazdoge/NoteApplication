package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import app.JsonApplication;
import app.RestartGameController;
import interface_adapters.NavigationManagerJson;
import interface_adapters.nevagateallowcatepage.NevagateAllowcateController;

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
        final String fontname = new String("Arial");
        titleLabel.setFont(new Font(fontname, Font.BOLD, Constants.THIRTY));
        container.add(titleLabel);

        // Set button fonts
        buttonSetting(fontname);

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

    private void buttonSetting(String fontname) {
        newGameButton.setFont(new Font(fontname, Font.BOLD, Constants.TWENTY));
        rankingButton.setFont(new Font(fontname, Font.PLAIN, Constants.TWENTY));
        quitButton.setFont(new Font(fontname, Font.PLAIN, Constants.TWENTY));
        logoutButton.setFont(new Font(fontname, Font.PLAIN, Constants.TWENTY));

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
    }

    /**
     * Sets the {@code NevigateAllowcateController} for this instance.
     *
     * <p>This method associates a {@code NevigateAllowcateController} with the current
     * instance of the {@code MainView} and logs the instance's identity hash code for
     * debugging purposes.</p>
     *
     * @param nevagateAllowcateController the {@code NevigateAllowcateController} to be set
     */
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
        final String error = new String("Error");
        newGameButton.addActionListener(event -> {
            restartGameController.resetGame();
            if (nevagateAllowcateController != null) {
                nevagateAllowcateController.execute();
            }
            else {
                JOptionPane.showMessageDialog(this, "Navigation controller not initialized.",
                        error, JOptionPane.ERROR_MESSAGE);
            }
        });

        // Switch to RankView when "Ranking" is clicked
        rankingButton.addActionListener(event -> {
            if (navigationManager != null) {
                navigationManager.showRankingView();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Navigation Manager is not initialized. Cannot navigate to Ranking View.",
                        error, JOptionPane.ERROR_MESSAGE);
            }
        });

        // Exit the game when "Quit" is clicked
        quitButton.addActionListener(event -> {
            System.exit(0);
        });

        // Switch to LoginView when "Logout" is clicked
        logoutButton.addActionListener(event -> {
            if (navigationManager != null) {
                navigationManager.showLoginView();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Error navigating to LoginView.",
                        error, JOptionPane.ERROR_MESSAGE);
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
    /**
     * Renders the component by making it visible to the user.
     *
     * <p>This method sets the visibility of the component to {@code true},
     * ensuring it is displayed on the screen.</p>
     */
    public void render() {
        setVisible(true);
    }

    /**
     * Hides the component by making it invisible to the user.
     *
     * <p>This method sets the visibility of the component to {@code false},
     * ensuring it is not displayed on the screen.</p>
     */
    public void disrender() {
        setVisible(false);
    }

    public static void main(String[] args) {
        try {
            new JsonApplication("PlayerFile", "PlayerFile", "RankingFile");
        }
        catch (IOException event) {
            event.printStackTrace();
        }
    }
}
