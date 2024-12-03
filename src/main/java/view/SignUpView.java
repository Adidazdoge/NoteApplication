package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import app.JsonApplication;
import interface_adapters.NavigationManagerJson;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupInterface;

/**
 * SignUpView handles the UI for user registration and integrates with the SignupApplication.
 */
public class SignUpView extends JFrame implements SignupInterface {
    private final JLabel nameLabel = new JLabel("60 Days to Survive", JLabel.CENTER);
    private final SpringLayout springLayout = new SpringLayout();
    private final JPanel centerPanel = new JPanel(springLayout);
    private final JLabel userNameLabel = new JLabel("Username:");
    private final JTextField userText = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordText = new JPasswordField();
    private final JLabel againLabel = new JLabel("Confirm Password:");
    private final JPasswordField againText = new JPasswordField();
    private final JButton registerButton = new JButton("Sign up");
    private final JButton loginButton = new JButton("Back to login");

    private SignupController signupController;
    private NavigationManagerJson navigationManager;

    /**
     * Constructs the SignUpView with the provided SignupController.
     *
     * @throws IOException If there is an error initializing the signup application.
     */
    public SignUpView() throws IOException {
        super("Sign Up");

        final Container contentPane = getContentPane();
        final String fontname = new String("Arial");
        final String error = new String("error");
        setFont(fontname);

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
        final int offsetX = (childWidth.getValue() + 40) / 2;
        // set userNameLabel location
        extracted(offsetX);

        registerButton.addActionListener(event -> handleSignup());

        loginButton.addActionListener(event -> {
            try {
                navigationManager.showLoginView();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error navigating to LoginView.",
                        error, JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        // setLocation(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FIVE_HUNDRED, Constants.THREE_HUNDRED);
    }

    private void setFont(String fontname) {
        nameLabel.setFont(new Font("Impact", Font.BOLD, Constants.FIFTY));
        userNameLabel.setFont(new Font(fontname, Font.BOLD, Constants.FIFTEEN));
        passwordLabel.setFont(new Font(fontname, Font.BOLD, Constants.FIFTEEN));
        againLabel.setFont(new Font(fontname, Font.BOLD, Constants.FIFTEEN));
        registerButton.setFont(new Font(fontname, Font.PLAIN, Constants.TWENTY));
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(Constants.THEME_COLOR);
        loginButton.setFont(new Font(fontname, Font.PLAIN, Constants.TWENTY));
        loginButton.setBackground(Constants.THEME_COLOR);
        loginButton.setForeground(Color.WHITE);
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
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, Constants.FIFTY, SpringLayout.NORTH,
                centerPanel);
        // set userText location
        springLayout.putConstraint(SpringLayout.WEST, userText, Constants.TEN, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userText, -Constants.FIVE, SpringLayout.NORTH, userNameLabel);
        // set passwordLabel location
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, Constants.TWENTY, SpringLayout.SOUTH,
                userNameLabel);
        // set passwordText location
        springLayout.putConstraint(SpringLayout.WEST, passwordText, Constants.TEN, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordText, -Constants.FIVE, SpringLayout.NORTH,
                passwordLabel);
        // set loginButton location
        springLayout.putConstraint(SpringLayout.WEST, loginButton, Constants.TEN, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, Constants.EIGHTY, SpringLayout.SOUTH,
                passwordLabel);
        // set registerButton location
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.FORTY, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, -Constants.FIVE, SpringLayout.NORTH,
                loginButton);
        // set againLabel location
        springLayout.putConstraint(SpringLayout.EAST, againLabel, 0, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againLabel, Constants.TWENTY, SpringLayout.SOUTH, passwordLabel);
        // set againText location
        springLayout.putConstraint(SpringLayout.WEST, againText, Constants.TEN, SpringLayout.EAST, againLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againText, -Constants.FIVE, SpringLayout.NORTH, againLabel);
    }

    private void handleSignup() {
        final String username = userText.getText();
        final String password = new String(passwordText.getPassword());
        final String confirmPassword = new String(againText.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            passwordText.setText("");
            againText.setText("");
            return;
        }

        signupController.handleSignup(username, password);
    }

    /**
     * Sets the LoginController for this view.
     *
     * @param signupController The LoginController instance.
     */
    public void setSignupController(SignupController signupController) {
        this.signupController = signupController;
    }

    /**
     * Sets the NavigationManager for this view.
     *
     * @param navigationManager The NavigationManager instance.
     */
    public void setNavigationManager(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
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
            if (navigationManager != null) {
                navigationManager.showLoginView();
            }
            else {
                userText.setText("");
                passwordText.setText("");
                againText.setText("");
                JOptionPane.showMessageDialog(this,
                        "Error navigating to LoginView.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        userText.setText("");
        passwordText.setText("");
        againText.setText("");
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
