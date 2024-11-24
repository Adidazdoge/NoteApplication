package view;

import javax.swing.*;
import java.awt.*;



/**
 * Constants used in this program.
 */
public class LoginView extends JFrame {
    private JLabel nameLabel = new JLabel("group project", JLabel.CENTER);
    private SpringLayout springLayout = new SpringLayout();
    private JPanel centerPanel = new JPanel(springLayout);
    private JLabel userNameLabel = new JLabel("username");
    private static JTextField userText = new JTextField();
    private JLabel passwordLabel = new JLabel("password");
    private static JPasswordField passwordText = new JPasswordField();
    private static JButton loginBotton = new JButton("log in");
    private static JButton registerButton = new JButton("sign up");

    public LoginView() {
        super("Log in");
        final Container contentPane = getContentPane();
        // set size
        final Font nameFont = new Font("12", Font.PLAIN, Constants.FORTY);
        nameLabel.setFont(nameFont);
        final Font centerFont = new Font("8", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        passwordLabel.setFont(centerFont);
        registerButton.setFont(centerFont);
        loginBotton.setFont(centerFont);
        userText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));
        passwordText.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.THIRTY));

        // add components
        centerPanel.add(userNameLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);
        centerPanel.add(loginBotton);
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

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        // setLocation(null);
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
        springLayout.putConstraint(SpringLayout.WEST, loginBotton, Constants.FIFTY, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBotton, Constants.TWENTY,
                SpringLayout.SOUTH, passwordLabel);
        // set registerButton
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.SIXTY, SpringLayout.EAST, loginBotton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 0, SpringLayout.NORTH, loginBotton);
    }

    // public static void main(String[] args) {
    // new LoginView();
    // }

    public static String getUser() {
        return userText.getText();
    }

    public static String getPwd() {
        return passwordText.getText();
    }

    public static JButton getLoginButton() {
        return loginBotton;
    }

    public static JButton getRegisterButton() {
        return registerButton;
    }
}