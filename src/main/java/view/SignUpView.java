package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Sign up view.
 */
public class SignUpView extends JFrame {
    private JLabel nameLabel = new JLabel("group project", JLabel.CENTER);
    private SpringLayout springLayout = new SpringLayout();
    private JPanel centerPanel = new JPanel(springLayout);
    private JLabel userNameLabel = new JLabel("username");
    private JTextField userText = new JTextField();
    private JLabel passwordLabel = new JLabel("password");
    private JPasswordField passwordText = new JPasswordField();
    private JButton loginBotton = new JButton("log in");
    private JButton registerButton = new JButton("sign up");
    private JLabel againLabel = new JLabel("again");
    private JTextField againText = new JTextField();

    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    public SignUpView() {
        super("sign up");
        final Container contentPane = getContentPane();
        // set size
        final Font nameFont = new Font("12", Font.PLAIN, 40);
        nameLabel.setFont(nameFont);
        final Font centerFont = new Font("8", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        passwordLabel.setFont(centerFont);
        againLabel.setFont(centerFont);
        registerButton.setFont(centerFont);
        loginBotton.setFont(centerFont);
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

        final ActionListener openGameViewListener = e -> {
            dispose();
            new GameView();
        };

        final ActionListener openLoginListener = e -> {
            dispose();
            new LoginView();
        };

        loginBotton.addActionListener(openLoginListener);
        registerButton.addActionListener(openGameViewListener);

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        // setLocation(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void extracted() {
        centerPanel.add(userNameLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);
        centerPanel.add(loginBotton);
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
        springLayout.putConstraint(SpringLayout.WEST, loginBotton, Constants.FIFTY, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBotton, Constants.EIGHTY, SpringLayout.SOUTH,
                passwordLabel);
        // set registerButton location
        springLayout.putConstraint(SpringLayout.WEST, registerButton, Constants.SIXTY, SpringLayout.EAST, loginBotton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 0, SpringLayout.NORTH, loginBotton);
        // set againLabel location
        springLayout.putConstraint(SpringLayout.EAST, againLabel, 0, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againLabel, Constants.TEN, SpringLayout.SOUTH, passwordLabel);
        // set againText location
        springLayout.putConstraint(SpringLayout.WEST, againText, Constants.TEN, SpringLayout.EAST, againLabel);
        springLayout.putConstraint(SpringLayout.NORTH, againText, 0, SpringLayout.NORTH, againLabel);
    }

    public static void main(String[] args) {
        new SignUpView();
    }

}
