package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JButton startGame = new JButton("Start Game");
    private SpringLayout springLayout = new SpringLayout();
    private JButton skill = new JButton("Skill");
    private JButton info = new JButton("Information");
    private JButton rank = new JButton("Ranking");
    private JButton quit = new JButton("Quit");
    private JPanel mainPanel = new JPanel(springLayout);
    private JLabel titleLabel = new JLabel("Group project", JLabel.CENTER);

    public MainView() {
        super("Main Menu");
        final Container contentPane = getContentPane();
        titleLabel.setFont(new Font("Serif", Font.PLAIN, Constants.THIRTY));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        final Font centerFont = new Font("8", Font.PLAIN, 20);
        startGame.setFont(centerFont);
        skill.setFont(centerFont);
        info.setFont(centerFont);
        rank.setFont(centerFont);
        quit.setFont(centerFont);
        mainPanel.add(startGame);
        mainPanel.add(skill);
        mainPanel.add(info);
        mainPanel.add(rank);
        mainPanel.add(quit);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 0, SpringLayout.NORTH, mainPanel);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, startGame, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, startGame, Constants.TWENTY, SpringLayout.SOUTH, titleLabel);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, skill, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, skill, Constants.TWENTY, SpringLayout.SOUTH, startGame);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, info, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, info, Constants.TWENTY, SpringLayout.SOUTH, skill);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rank, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, rank, Constants.TWENTY, SpringLayout.SOUTH, info);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quit, 0,
                SpringLayout.HORIZONTAL_CENTER, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, quit, Constants.TWENTY, SpringLayout.SOUTH, rank);

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new MainView();
    }

}
