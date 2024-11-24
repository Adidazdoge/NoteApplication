package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RankingView extends JFrame {
    public RankingView() {
        super("Ranking");
        final Container container = getContentPane();
        container.setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Ranking", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.THIRTY));
        container.add(titleLabel, BorderLayout.NORTH);

        // jtable example
        final String[] columnNames = {"Rank", "Player", "Score"};
        final Object[][] data = new Object[Constants.ONE_HUNDRED][Constants.THREE];
        for (int i = 0; i < Constants.ONE_HUNDRED; i++) {
            // Rank
            data[i][0] = "No." + (i + 1);
            // Player name (example)
            data[i][1] = "Player" + (i + 1);
            // Random score
            data[i][2] = (int) (Math.random() * Constants.ONE_THOUSAND);
        }

        final JTable rankingTable = new JTable(new DefaultTableModel(data, columnNames));
        rankingTable.setEnabled(false);
        final JScrollPane scrollPane = new JScrollPane(rankingTable);
        container.add(scrollPane, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 1));

        // example rank
        final JLabel yourRankLabel = new JLabel("Your Rank: No. 10", JLabel.CENTER);
        yourRankLabel.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        bottomPanel.add(yourRankLabel);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Constants.TWENTY, Constants.TEN));

        final JButton mainButton = new JButton("Main");
        final JButton quitButton = new JButton("Quit");
        buttonPanel.add(mainButton);
        buttonPanel.add(quitButton);

        bottomPanel.add(buttonPanel);

        container.add(bottomPanel, BorderLayout.SOUTH);

        // 窗口设置
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RankingView();
    }
}