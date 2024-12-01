package view;

import javax.swing.*;
import java.awt.*;
/**
 * Character creation view.
 */

public class CharacterCreationView extends JFrame {
    private int points = Constants.TWENTY;
    private final JLabel pointsLabel;
    private final JLabel socialLabel;
    private final JLabel luckLabel;
    private final JLabel mobilizationLabel;
    private final JLabel thriftLabel;
    private final JLabel generalshipLabel;
    private int social = 0;
    private int luck = 0;
    private int mobilization = 0;
    private int thrift = 0;
    private int generalship = 0;

    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings",
                       "checkstyle:ExecutableStatementCount"})
    public CharacterCreationView() {
        super("Build Your Character");

        // Set up container and layout
        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Points Label
        pointsLabel = new JLabel("Points: " + points);
        pointsLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(pointsLabel);

        // Attribute Labels and Buttons
        socialLabel = new JLabel("Social: " + social);
        final JButton socialButton = createButton("Social");
        container.add(socialLabel);
        container.add(socialButton);

        luckLabel = new JLabel("Luck: " + luck);
        final JButton luckButton = createButton("Luck");
        container.add(luckLabel);
        container.add(luckButton);

        mobilizationLabel = new JLabel("Mobilization: " + mobilization);
        final JButton mobilizationButton = createButton("Mobilization");
        container.add(mobilizationLabel);
        container.add(mobilizationButton);

        thriftLabel = new JLabel("Thrift: " + thrift);
        final JButton thriftButton = createButton("Thrift");
        container.add(thriftLabel);
        container.add(thriftButton);

        generalshipLabel = new JLabel("Generalship: " + generalship);
        final JButton generalshipButton = createButton("Generalship");
        container.add(generalshipLabel);
        container.add(generalshipButton);

        // Back and Start Game Buttons
        final JButton backButton = new JButton("Back");
        final JButton startGameButton = new JButton("Start Game");
        container.add(backButton);
        container.add(startGameButton);

        // Layout constraints
        extracted(layout, container, socialButton, luckButton, mobilizationButton,
                thriftButton, generalshipButton, backButton, startGameButton);

        // Button Action Listeners
        addListeners(socialButton, luckButton, mobilizationButton, thriftButton, generalshipButton);

        backButton.addActionListener(e -> {
            dispose();
            new MainView();
        });

        startGameButton.addActionListener(e -> {
            dispose();
            new GameView();
        });

        // Window settings
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    private void addListeners(JButton socialButton, JButton luckButton, JButton mobilizationButton, JButton thriftButton, JButton generalshipButton) {
        socialButton.addActionListener(e -> updatePoints("Social"));
        luckButton.addActionListener(e -> updatePoints("Luck"));
        mobilizationButton.addActionListener(e -> updatePoints("Mobilization"));
        thriftButton.addActionListener(e -> updatePoints("Thrift"));
        generalshipButton.addActionListener(e -> updatePoints("Generalship"));
    }

    private void extracted(SpringLayout layout, Container container, JButton socialButton, JButton luckButton,
                           JButton mobilizationButton, JButton thriftButton, JButton generalshipButton,
                           JButton backButton, JButton startGameButton) {
        layout.putConstraint(SpringLayout.WEST, pointsLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, pointsLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, socialLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, socialLabel, Constants.TWENTY, SpringLayout.SOUTH, pointsLabel);
        layout.putConstraint(SpringLayout.WEST, socialButton, Constants.TWENTY, SpringLayout.EAST, socialLabel);
        layout.putConstraint(SpringLayout.NORTH, socialButton, 0, SpringLayout.NORTH, socialLabel);

        layout.putConstraint(SpringLayout.WEST, luckLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, luckLabel, Constants.TWENTY, SpringLayout.SOUTH, socialLabel);
        layout.putConstraint(SpringLayout.WEST, luckButton, Constants.TWENTY, SpringLayout.EAST, luckLabel);
        layout.putConstraint(SpringLayout.NORTH, luckButton, 0, SpringLayout.NORTH, luckLabel);

        layout.putConstraint(SpringLayout.WEST, mobilizationLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, mobilizationLabel, Constants.TWENTY, SpringLayout.SOUTH, luckLabel);
        layout.putConstraint(SpringLayout.WEST, mobilizationButton, Constants.TWENTY, SpringLayout.EAST, mobilizationLabel);
        layout.putConstraint(SpringLayout.NORTH, mobilizationButton, 0, SpringLayout.NORTH, mobilizationLabel);

        layout.putConstraint(SpringLayout.WEST, thriftLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, thriftLabel, Constants.TWENTY, SpringLayout.SOUTH, mobilizationLabel);
        layout.putConstraint(SpringLayout.WEST, thriftButton, Constants.TWENTY, SpringLayout.EAST, thriftLabel);
        layout.putConstraint(SpringLayout.NORTH, thriftButton, 0, SpringLayout.NORTH, thriftLabel);

        layout.putConstraint(SpringLayout.WEST, generalshipLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, generalshipLabel, Constants.TWENTY, SpringLayout.SOUTH, thriftLabel);
        layout.putConstraint(SpringLayout.WEST, generalshipButton, Constants.TWENTY, SpringLayout.EAST, generalshipLabel);
        layout.putConstraint(SpringLayout.NORTH, generalshipButton, 0, SpringLayout.NORTH, generalshipLabel);

        layout.putConstraint(SpringLayout.WEST, backButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.SOUTH, backButton, -Constants.TWENTY, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.EAST, startGameButton, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.SOUTH, startGameButton, -Constants.TWENTY, SpringLayout.SOUTH, container);
    }

    private JButton createButton(String text) {
        final JButton button = new JButton("+");
        button.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        button.setActionCommand(text);
        return button;
    }

    private void updatePoints(String attribute) {
        if (points > 0) {
            switch (attribute) {
                case "Social":
                    social++;
                    socialLabel.setText("Social: " + social);
                    break;
                case "Luck":
                    luck++;
                    luckLabel.setText("Luck: " + luck);
                    break;
                case "Mobilization":
                    mobilization++;
                    mobilizationLabel.setText("Mobilization: " + mobilization);
                    break;
                case "Thrift":
                    thrift++;
                    thriftLabel.setText("Thrift: " + thrift);
                    break;
                case "Generalship":
                    generalship++;
                    generalshipLabel.setText("Generalship: " + generalship);
                    break;
            }
            points--;
            pointsLabel.setText("Points: " + points);
        }
        else {
            JOptionPane.showMessageDialog(this, "No points left!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CharacterCreationView();
    }
}