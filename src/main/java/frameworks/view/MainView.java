package frameworks.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JButton newGame = new JButton("New Game");
    private JButton ranking = new JButton("Ranking");
    private JButton quit = new JButton("Quit");
    private JButton logout = new JButton("Logout");
    private SpringLayout springLayout = new SpringLayout();
    private JPanel mainPanel = new JPanel(springLayout);
    private JLabel titleLabel = new JLabel("60 Days to Survive", JLabel.CENTER);

    public MainView() {
        super("Main Menu");
        final Container contentPane = getContentPane();
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        final Font buttonFont = new Font("Serif", Font.PLAIN, Constants.TWENTY);
        newGame.setFont(buttonFont);
        ranking.setFont(buttonFont);
        quit.setFont(buttonFont);
        logout.setFont(buttonFont);

        mainPanel.add(newGame);
        mainPanel.add(ranking);
        mainPanel.add(quit);
        mainPanel.add(logout);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        // Adjust constraints for title
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);

        // Button placement
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGame, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, newGame, Constants.FIFTY, SpringLayout.NORTH, mainPanel);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ranking, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, ranking, Constants.TWENTY, SpringLayout.SOUTH, newGame);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quit, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, quit, Constants.TWENTY, SpringLayout.SOUTH, ranking);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logout, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, logout, Constants.TWENTY, SpringLayout.SOUTH, quit);

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
    public JButton getNewGameButton() {
        return newGame;
    }

    public JButton getRankingButton() {
        return ranking;
    }

    public JButton getQuitButton() {
        return quit;
    }

    public JButton getLogoutButton() {
        return logout;
    }
}

