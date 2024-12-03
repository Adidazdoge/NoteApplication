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
    private final JLabel nameLabel = new JLabel("60 Days to Survive", JLabel.CENTER);
    private final SpringLayout springLayout = new SpringLayout();
    private final JPanel centerPanel = new JPanel(springLayout);
    private final JLabel userNameLabel = new JLabel("Username:");
    private final JTextField userText = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordText = new JPasswordField();
    private final JButton loginButton = new JButton("Log in");
    private final JButton registerButton = new JButton("Sign up");

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
        nameLabel.setFont(new Font("Impact", Font.BOLD, Constants.FIFTY));
        userNameLabel.setFont(new Font("Arial", Font.BOLD, Constants.FIFTEEN));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, Constants.FIFTEEN));
        loginButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        loginButton.setBackground(Constants.THEME_COLOR);
        loginButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(Constants.THEME_COLOR);

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
        final int offsetX = (childWidth.getValue() + 40) / 2;
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
                final SignUpView signUpView = new SignUpView();
                signUpView.render();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FOUR_HUNDRED, Constants.TWO_HUNDRED);
    }

    private void layout(int offsetX) {
        // set userLable location
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HEIGHT, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, Constants.SIXTY, SpringLayout.NORTH,
                centerPanel);
        // set userText location
        springLayout.putConstraint(SpringLayout.WEST, userText, Constants.TEN, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userText, -Constants.FIVE, SpringLayout.NORTH, userNameLabel);
        // set passwordLabel location
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, Constants.TWENTY, SpringLayout.SOUTH, userNameLabel);
        // set passwordText location
        springLayout.putConstraint(SpringLayout.WEST, passwordText, Constants.TEN, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordText, -Constants.FIVE, SpringLayout.NORTH, passwordLabel);
        // set loginButton location
        springLayout.putConstraint(SpringLayout.WEST, loginButton, Constants.FIFTY, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, Constants.FORTY,
                SpringLayout.SOUTH, passwordLabel);
        // set registerButton
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.FIFTY, SpringLayout.EAST, loginButton);
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
            final MainView mainView = new MainView();
            mainView.render();
        }
    }

    // Add render method
    public void render() {
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        final LoginView loginView = new LoginView();
        loginView.render();
    }
}
