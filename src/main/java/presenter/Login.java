package presenter;

import view.LoginView;
import view.MainView;
import view.SignUpView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    private LoginView loginView;
    private MainView mainView = new MainView();
    private SignUpView signUpView = new SignUpView();

    public Login(LoginView loginView) {
        this.loginView = new LoginView();
        final JButton loginButton = LoginView.getLoginButton();
        final JButton registerButton = LoginView.getRegisterButton();
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        mainView.dispose();
        signUpView.dispose();
        loginView.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final JButton source = (JButton) e.getSource();

        if (source.getText().equals("log in")) {
            final String username = loginView.getUser();
            final String password = new String(loginView.getPwd());
            // check username and password
            if (isValidUser(username, password)) {

                JOptionPane.showMessageDialog(loginView, "Login successful! Welcome to the Main Menu.");
                loginView.dispose();
                new MainMenu(mainView);
            }
            else {
                // If doesn't match
                JOptionPane.showMessageDialog(loginView, "Invalid username or password. Please try again.");
            }

        }
        else if (source.getText().equals("sign up")) {
            new SignUp(signUpView);
        }
    }

    private boolean isValidUser(String username, String password) {
        // example
        return "admin".equals(username) && "password123".equals(password);
    }

    public static void main(String[] args) {
        final LoginView loginView = new LoginView();
        new Login(loginView);
    }
}

