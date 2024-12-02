package view;

import app.LoginApplication;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * LoginView handles the UI for user login and integrates with the LoginApplication.
 */
public class LoginView extends JFrame implements LoginInterface {
    private final JLabel nameLabel = new JLabel("Group Project", JLabel.CENTER);
    private final SpringLayout springLayout = new SpringLayout();
    private final JPanel centerPanel = new JPanel(springLayout);
    private final JLabel userNameLabel = new JLabel("Username:");
    private final JTextField userText = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordText = new JPasswordField();
    private final JButton loginButton = new JButton("Log In");
    private final JButton registerButton = new JButton("Sign Up");

    private final LoginController loginController;

    /**
     * Constructs the LoginView with the provided LoginController.
     *
     * @throws IOException If there is an error initializing the login application.
     */
    public LoginView() throws IOException {
        super("Login");

        // Initialize the controller via the LoginApplication
        this.loginController = LoginApplication.initializeLogin(this);

        final Container contentPane = getContentPane();
        nameLabel.setFont(new Font("Arial", Font.PLAIN, Constants.FORTY));
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        loginButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        registerButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));

        userText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));
        passwordText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));

        centerPanel.add(userNameLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);
        centerPanel.add(loginButton);
        centerPanel.add(registerButton);

        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // spring layout
        final Spring titleLabelWidth = Spring.width(userNameLabel);
        final Spring titleTextWidth = Spring.width(userText);
        final Spring spaceWidth = Spring.constant(20);
        final Spring childWidth = Spring.sum(Spring.sum(titleLabelWidth, titleTextWidth), spaceWidth);
        final int offsetX = childWidth.getValue() / 2;
        layout(offsetX);

        loginButton.addActionListener(e -> {
            final String username = userText.getText();
            final String password = new String(passwordText.getPassword());
            loginController.handleLogin(username, password);
        });

        registerButton.addActionListener(e -> {
            dispose();
            try {
                // Navigate to the sign-up page
                new SignUpView();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void layout(int offsetX) {
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
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, Constants.TWENTY,
                SpringLayout.SOUTH, passwordLabel);
        // set registerButton
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.SIXTY, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 0, SpringLayout.NORTH, loginButton);

    }

    /**
     * Displays the result of a login attempt.
     *
     * @param message A message indicating the result of the login attempt.
     */
    @Override
    public void displayLoginResult(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Result", JOptionPane.INFORMATION_MESSAGE);

        if ("Login successful!".equals(message)) {
            // Close login window
            dispose();
            // Navigate to the game view
            new GameView();
        }
    }

    public static void main(String[] args) {
        try {
            new LoginView();
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error initializing the login system.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
