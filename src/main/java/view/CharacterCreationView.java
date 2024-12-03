package view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import interface_adapters.nevagatemainview.NevagateMainController;
import interface_adapters.startallowcatepoint.AllowcateController;
import interface_adapters.startallowcatepoint.AllowcateInterface;

/**
 * Character creation view that allows players to allocate points to various attributes
 * such as Social, Luck, Mobilization, Thrift, and Generalship.
 * This view also provides navigation to the main menu and starting the game.
 */
public class CharacterCreationView extends JFrame implements AllowcateInterface {
    public static final String TEXT_SOCIAL = "Social";
    public static final String TEXT_LUCK = "Luck";
    public static final String TEXT_THRIFT = "Thrift";
    public static final String TEXT_MOBILIZATION = "Mobilization";
    public static final String TEXT_GENERALSHIP = "Generalship";
    private int points = Constants.TWENTY;
    private final JLabel pointsLabel;
    private final JLabel socialLabel;
    private final JLabel luckLabel;
    private final JLabel mobilizationLabel;
    private final JLabel thriftLabel;
    private final JLabel generalshipLabel;
    private int social;
    private int luck;
    private int mobilization;
    private int thrift;
    private int generalship;
    private AllowcateController allowcateController;
    private NevagateMainController nevagateMainController;

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
        final JButton socialButton = createButton(TEXT_SOCIAL);
        container.add(socialLabel);
        container.add(socialButton);

        luckLabel = new JLabel("Luck: " + luck);
        final JButton luckButton = createButton(TEXT_LUCK);
        container.add(luckLabel);
        container.add(luckButton);

        mobilizationLabel = new JLabel("Mobilization: " + mobilization);
        final JButton mobilizationButton = createButton(TEXT_MOBILIZATION);
        container.add(mobilizationLabel);
        container.add(mobilizationButton);

        thriftLabel = new JLabel("Thrift: " + thrift);
        final JButton thriftButton = createButton(TEXT_THRIFT);
        container.add(thriftLabel);
        container.add(thriftButton);

        generalshipLabel = new JLabel("Generalship: " + generalship);
        final JButton generalshipButton = createButton(TEXT_GENERALSHIP);
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
            nevagateMainController.execute();
        });

        startGameButton.addActionListener(e -> {
            allowcateController.execute(points, social, luck, thrift, mobilization, generalship);
        });

        // Window settings
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FIVE_HUNDRED, Constants.THREE_HUNDRED);
        setVisible(false);
    }

    /**
     * Sets the AllowcateController and NevagateMainController for this instance.
     *
     * @param AllowcateController the instance of {@code AllowcateController} to set
     * @param NevagateMainController the instance of {@code NevagateMainController} to set
     */
    public void setAllowcateController(AllowcateController AllowcateController,
                                       NevagateMainController NevagateMainController) {
        this.allowcateController = AllowcateController;
        this.nevagateMainController = NevagateMainController;
    }

    /**
     * Adds action listeners to the buttons for allocating points to attributes.
     *
     * @param socialButton       Button to increase Social attribute.
     * @param luckButton         Button to increase Luck attribute.
     * @param mobilizationButton Button to increase Mobilization attribute.
     * @param thriftButton       Button to increase Thrift attribute.
     * @param generalshipButton  Button to increase Generalship attribute.
     */
    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    private void addListeners(JButton socialButton, JButton luckButton, JButton mobilizationButton,
                              JButton thriftButton, JButton generalshipButton) {
        socialButton.addActionListener(e -> updatePoints(TEXT_SOCIAL));
        luckButton.addActionListener(e -> updatePoints(TEXT_LUCK));
        mobilizationButton.addActionListener(e -> updatePoints(TEXT_MOBILIZATION));
        thriftButton.addActionListener(e -> updatePoints(TEXT_THRIFT));
        generalshipButton.addActionListener(e -> updatePoints(TEXT_GENERALSHIP));
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
        layout.putConstraint(SpringLayout.WEST, mobilizationButton, Constants.TWENTY, SpringLayout.EAST,
                mobilizationLabel);
        layout.putConstraint(SpringLayout.NORTH, mobilizationButton, 0, SpringLayout.NORTH, mobilizationLabel);

        layout.putConstraint(SpringLayout.WEST, thriftLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, thriftLabel, Constants.TWENTY, SpringLayout.SOUTH, mobilizationLabel);
        layout.putConstraint(SpringLayout.WEST, thriftButton, Constants.TWENTY, SpringLayout.EAST, thriftLabel);
        layout.putConstraint(SpringLayout.NORTH, thriftButton, 0, SpringLayout.NORTH, thriftLabel);

        layout.putConstraint(SpringLayout.WEST, generalshipLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, generalshipLabel, Constants.TWENTY, SpringLayout.SOUTH, thriftLabel);
        layout.putConstraint(SpringLayout.WEST, generalshipButton, Constants.TWENTY, SpringLayout.EAST,
                generalshipLabel);
        layout.putConstraint(SpringLayout.NORTH, generalshipButton, 0, SpringLayout.NORTH, generalshipLabel);

        layout.putConstraint(SpringLayout.WEST, backButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.SOUTH, backButton, -Constants.TWENTY, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.EAST, startGameButton, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.SOUTH, startGameButton, -Constants.TWENTY, SpringLayout.SOUTH, container);
    }

    /**
     * Creates a button with a given label and font style.
     *
     * @param text The text to display on the button.
     * @return A new JButton instance.
     */
    private JButton createButton(String text) {
        final JButton button = new JButton("+");
        button.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        button.setActionCommand(text);
        return button;
    }

    /**
     * Updates the points allocation for a specific attribute.
     *
     * @param attribute The name of the attribute to update.
     */
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
                default:
                    // No action needed
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

    /**
     * Renders the component by setting its size and making it visible.
     * The size is set to 400x600 pixels.
     */
    public void render() {
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setVisible(true);
    }

    /**
     * Disables the rendering of the component by changing its size and making it invisible.
     * The size is set to 400x400 pixels.
     */
    public void disrender() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(false);
    }

    /**
     * Displays a failure message when allocation fails.
     *
     * @param message The error message to display.
     */
    @Override
    public void failureAllowcate(String message) {
        // Display the failure message in a dialog box
        JOptionPane.showMessageDialog(
                this,
                message,
                "Allocation Failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
