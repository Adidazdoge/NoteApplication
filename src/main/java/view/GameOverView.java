package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import app.RestartGameController;
import interface_adapters.NavigationManagerJson;
import interface_adapters.endprocesshorde.HordeInterface;
import interface_adapters.gamelosedetecter.LoseInterface;
import interface_adapters.nevagatemainview.NevagateMainController;
import interface_adapters.nevagatemainview.NevagateMainInterface;

/**
 * Represents the "Game Over" view in the application.
 * Provides UI components for displaying the game result and returning to the main menu.
 */
public class GameOverView extends JFrame implements LoseInterface, HordeInterface, NevagateMainInterface {
    // Make it an instance variable
    private final JLabel scoreLabel;
    // Make it an instance variable
    private final JTextArea descriptionArea;
    private NevagateMainController nevagateMainController;
    private NavigationManagerJson navigationManager;
    private RestartGameController restartGameController;

    /**
     * Constructs the GameOverView UI and initializes all components.
     */
    public GameOverView() {
        super("Game Over");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Title label
        final JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(titleLabel);

        // Score label
        // Initialize with a default value
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        container.add(scoreLabel);

        // Description area
        descriptionArea = new JTextArea("Game description goes here...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setLineWrap(true);
        // Ensure long text wraps
        descriptionArea.setWrapStyleWord(true);
        container.add(descriptionArea);

        // Main menu button
        final JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        container.add(mainMenuButton);

        // Layout constraints
        extracted(layout, titleLabel, container, scoreLabel, descriptionArea, mainMenuButton);

        // Add action listener to "Main Menu" button
        mainMenuButton.addActionListener(event -> {
            restartGameController.resetGame();
            navigationManager.showMainView();
            dispose();
        });

        // Window settings
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    public void setController(NevagateMainController ininevagateMainController) {
        this.nevagateMainController = ininevagateMainController;
    }

    public void setNavigationManager(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
    }

    public void setRestartGameController(RestartGameController restartGameController) {
        this.restartGameController = restartGameController;
    }

    private static void extracted(SpringLayout layout, JLabel titleLabel, Container container, JLabel scoreLabel,
                                  JTextArea descriptionArea, JButton mainMenuButton) {
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scoreLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, scoreLabel, Constants.TWENTY, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, descriptionArea, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, descriptionArea, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, descriptionArea, Constants.TWENTY, SpringLayout.SOUTH, scoreLabel);
        layout.putConstraint(SpringLayout.SOUTH, descriptionArea, -Constants.ONE_HUNDRED, SpringLayout.SOUTH,
                container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuButton, 0, SpringLayout.HORIZONTAL_CENTER,
                container);
        layout.putConstraint(SpringLayout.NORTH, mainMenuButton, Constants.TWENTY, SpringLayout.SOUTH, descriptionArea);
    }

    /**
     * Renders the component by initializing required events, setting its size,
     * and making it visible.
     *
     * <p>This method triggers the execution of the eventInitializerController
     * and adjusts the component's size to a predefined value. Finally, it makes
     * the component visible to the user.</p>
     */
    public void render() {
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setVisible(true);
    }

    /**
     * Disables the rendering of the component by maintaining its size but
     * making it invisible.
     *
     * <p>This method adjusts the component's size to a predefined value but
     * ensures that it is not visible to the user.</p>
     */
    public void disrender() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(false);
    }

    public static void main(String[] args) {
        new GameOverView();
    }

    @Override
    public void prepareGameOverEarly(String message, int score) {
        // Update the score label with the given score
        scoreLabel.setText("Score: " + score);

        // Update the description area with the provided message
        descriptionArea.setText(message);

        // Ensure the user can see the new content
        descriptionArea.setCaretPosition(0);

    }

    @Override
    public void updateUiHorde(String message, int score) {
        scoreLabel.setText("Score: " + score);

        // Update the description area with the provided message
        descriptionArea.setText(message);

        // Ensure the user can see the new content
        descriptionArea.setCaretPosition(0);

    }

    @Override
    public void failureHorde(String failmessage) {

    }
}
