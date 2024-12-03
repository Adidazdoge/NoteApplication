package view;

import app.SignupApplication;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * SignUpView handles the UI for user registration and integrates with the SignupApplication.
 */
public class SignUpView extends JFrame implements SignupInterface {
    private final JLabel nameLabel = new JLabel("Group Project", JLabel.CENTER);
    private final SpringLayout springLayout = new SpringLayout();
    private final JPanel centerPanel = new JPanel(springLayout);
    private final JLabel userNameLabel = new JLabel("Username:");
    private final JTextField userText = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordText = new JPasswordField();
    private final JLabel againLabel = new JLabel("Confirm Password:");
    private final JPasswordField againText = new JPasswordField();
    private final JButton registerButton = new JButton("Sign Up");
    private final JButton loginButton = new JButton("Back to Login");

    private final SignupController signupController;

    /**
     * Constructs the SignUpView with the provided SignupController.
     *
     * @throws IOException If there is an error initializing the signup application.
     */
    public SignUpView() throws IOException {
        super("Sign Up");

        // Initialize the controller via SignupApplication
        this.signupController = SignupApplication.initializeSignup(this);

        final Container contentPane = getContentPane();
        nameLabel.setFont(new Font("Arial", Font.PLAIN, Constants.FORTY));
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        againLabel.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        registerButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        loginButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));

        userText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));
        passwordText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));
        againText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));

        // add components
        extracted();

        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // springlayout
        final Spring titleLabelWidth = Spring.width(userNameLabel);
        final Spring titleTextWidth = Spring.width(userText);
        final Spring spaceWidth = Spring.constant(20);
        final Spring childWidth = Spring.sum(Spring.sum(titleLabelWidth, titleTextWidth), spaceWidth);
        final int offsetX = childWidth.getValue() / 2;
        // set userNameLabel location
        extracted(offsetX);

        registerButton.addActionListener(e -> handleSignup());
        loginButton.addActionListener(e -> {
            dispose();
            try {
                new LoginView();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error navigating to LoginView.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        // setLocation(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FOUR_HUNDRED, Constants.TWO_HUNDRED);
        setVisible(true);
    }

    private void extracted() {
        centerPanel.add(userNameLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);
        centerPanel.add(loginButton);
        centerPanel.add(registerButton);
        centerPanel.add(againLabel);
        centerPanel.add(againText);
    }

    private void extracted(int offsetX) {
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HEIGHT, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, Constants.TWENTY, SpringLayout.NORTH,
                centerPanel);
        // set userText location
        springLayout.putConstraint(SpringLayout.WEST, userText, Constants.TEN, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userText, 0, SpringLayout.NORTH, userNameLabel);
        // set passwordLabel location
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, Constants.TEN, SpringLayout.SOUTH, userNameLabel);
        // set passwordText location
        springLayout.putConstraint(SpringLayout.WEST, passwordText, Constants.TEN, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordText, 0, SpringLayout.NORTH, passwordLabel);
        // set loginButton location
        springLayout.putConstraint(SpringLayout.WEST, loginButton, Constants.FIFTY, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, Constants.EIGHTY, SpringLayout.SOUTH,
                passwordLabel);
        // set registerButton location
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.SIXTY, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 0, SpringLayout.NORTH, loginButton);
        // set againLabel location
        springLayout.putConstraint(SpringLayout.EAST, againLabel, 0, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againLabel, Constants.TEN, SpringLayout.SOUTH, passwordLabel);
        // set againText location
        springLayout.putConstraint(SpringLayout.WEST, againText, Constants.TEN, SpringLayout.EAST, againLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againText, 0, SpringLayout.NORTH, againLabel);
    }

    private void handleSignup() {
        final String username = userText.getText();
        final String password = new String(passwordText.getPassword());
        final String confirmPassword = new String(againText.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        signupController.handleSignup(username, password);
    }

    /**
     * Displays the result of the signup process.
     *
     * @param message A message indicating the result of the signup process.
     */
    @Override
    public void displaySignupResult(String message) {
        JOptionPane.showMessageDialog(this, message, "Signup Result", JOptionPane.INFORMATION_MESSAGE);

        if ("Signup successful!".equals(message)) {
            // Close signup view
            dispose();
            try {
                // Navigate to login page
                new LoginView();
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error navigating to LoginView.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        try {
            new SignUpView();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error initializing signup system.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
