package view;

import javax.swing.*;
import java.awt.*;
/**
 * Gameover view.
 */
public class GameOverView extends JFrame {
    public GameOverView() {
        super("Game Over");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(titleLabel);

        final JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        container.add(scoreLabel);

        final JTextArea descriptionArea = new JTextArea("Game description goes here...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(descriptionArea);

        final JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        container.add(mainMenuButton);

        // Layout constraints
        extracted(layout, titleLabel, container, scoreLabel, descriptionArea, mainMenuButton);

        // Add action listener to "Main Menu" button
        mainMenuButton.addActionListener(e -> {
            dispose();
            new MainView();
        });

        // Window settings
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
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
        layout.putConstraint(SpringLayout.SOUTH, descriptionArea, -Constants.ONE_HUNDRED,
                SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuButton, 0,
                SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, mainMenuButton, Constants.TWENTY, SpringLayout.SOUTH, descriptionArea);
    }

    public static void main(String[] args) {
        new GameOverView();
    }
}
