package view;

import app.JsonApplication;
import interface_adapters.NavigationManagerJson;
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

    private LoginController loginController;
    private NavigationManagerJson navigationManager;

    /**
     * Constructs the LoginView with the provided LoginController.
     *
     * @throws IOException If there is an error initializing the login application.
     */
    public LoginView() throws IOException {
        super("Login");

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
            userText.setText("");
            passwordText.setText("");
            loginController.handleLogin(username, password);
        });

        registerButton.addActionListener(e -> {
            if (navigationManager != null) {
                // Navigate to SignUpView
                navigationManager.showSignUpView();
            }
        });
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FIVE_HUNDRED, Constants.THREE_HUNDRED);
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
     * Sets the LoginController for this view.
     *
     * @param loginController The LoginController instance.
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
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
     * Displays the result of a login attempt.
     *
     * @param message A message indicating the result of the login attempt.
     */
    @Override
    public void displayLoginResult(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Result", JOptionPane.INFORMATION_MESSAGE);

        if ("Login successful!".equals(message)) {
            if (navigationManager != null) {
                navigationManager.showMainView();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Cannot navigate to Main View.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void render() {
        setVisible(true);
    }

    public void disrender() {
        setVisible(false);
    }

    public static void main(String[] args) throws IOException {
        try {
            new JsonApplication("PlayerFile", "PlayerFile", "RankingFile");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
