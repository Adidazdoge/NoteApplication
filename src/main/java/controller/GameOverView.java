package controller;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JFrame {
    public GameOverView() {
        super("Game Over");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.THIRTY));
        container.add(titleLabel);

        final JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        container.add(scoreLabel);

        final JButton rankingButton = new JButton("Ranking");
        rankingButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        container.add(rankingButton);

        final JButton startNewGameButton = new JButton("Start New Game");
        startNewGameButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        container.add(startNewGameButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scoreLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, scoreLabel, Constants.FORTY, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rankingButton, 0,
                SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, rankingButton, Constants.FIFTY, SpringLayout.SOUTH, scoreLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, startNewGameButton, 0,
                SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, startNewGameButton, Constants.THIRTY, SpringLayout.SOUTH, rankingButton);

        setSize(Constants.SIX_HUNDRED, Constants.SIX_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameOverView();
    }
}