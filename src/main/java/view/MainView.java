package view;

import app.RankingApplication;
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
    private JButton rankingButton = new JButton("Ranking");
    private JButton quitButton = new JButton("Quit");
    private JButton logoutButton = new JButton("Logout");
    private NevagateAllowcateController nevagateAllowcateController;

    @SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:SuppressWarnings"})
    public MainView() {
        // Set layout and container
        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Title
        final JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(titleLabel);

        // Set button fonts
        newGameButton.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        rankingButton.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        quitButton.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        logoutButton.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));

        // Add buttons to the container
        container.add(newGameButton);
        container.add(rankingButton);
        container.add(quitButton);
        container.add(logoutButton);

        // Layout constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, Constants.FORTY, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rankingButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, rankingButton, Constants.TWENTY, SpringLayout.SOUTH, newGameButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quitButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, quitButton, Constants.TWENTY, SpringLayout.SOUTH, rankingButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logoutButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, logoutButton, Constants.TWENTY, SpringLayout.SOUTH, quitButton);

        // Add listeners for buttons
        addListeners();

        // Set window properties (moved to render())
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FOUR_HUNDRED, Constants.TWO_HUNDRED);
    }

    public void setNevagateAllowcateController(NevagateAllowcateController nevagateAllowcateController) {
        this.nevagateAllowcateController = nevagateAllowcateController;
    }

    // Add ActionListener to buttons
    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    private void addListeners() {
        // Switch to GameView when "New Game" is clicked
        newGameButton.addActionListener(e -> {
            nevagateAllowcateController.execute();
        });

        // Switch to RankView when "Ranking" is clicked
        rankingButton.addActionListener(e -> {
            // Close MainView
            dispose();
            final RankingView rankingView = new RankingView();
            rankingView.render();
        });

        // Exit the game when "Quit" is clicked
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        // Switch to LoginView when "Logout" is clicked
        logoutButton.addActionListener(e -> {

        });
    }

    // Add render method
    public void render() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(true);
    }

    public void disrender() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(false);
    }

     public static void main(String[] args) {
        // Call render() to display the view
        new MainView().render();
     }
}
