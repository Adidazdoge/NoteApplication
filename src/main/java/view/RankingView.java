package view;

import app.JsonApplication;
import interface_adapters.NavigationManagerJson;
import interface_adapters.rankinglist.RankingController;
import interface_adapters.rankinglist.RankingInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Ranking view class that implements RankingInterface to display the leaderboard.
 */
public class RankingView extends JFrame implements RankingInterface {
    private final JTable rankingTable;
    private final DefaultTableModel tableModel;
    private final JButton backButton;

    private RankingController rankingController;
    private NavigationManagerJson navigationManager;

    /**
     * Constructs the RankingView and sets up the UI components.
     * @throws RuntimeException If there is an error initializing the signup application.
     */
    public RankingView() {
        super("Leaderboard");

        // Main container layout
        final Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Title
        final JLabel titleLabel = new JLabel("Ranking List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, Constants.THIRTY));
        container.add(titleLabel, BorderLayout.NORTH);

        // Table to display rankings
        final String[] columnNames = {"Rank", "Player Name", "Score", "Days Survived", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rankingTable = new JTable(tableModel);
        // Make table non-editable
        rankingTable.setEnabled(false);
        rankingTable.setFont(new Font("Arial", Font.PLAIN, Constants.FIFTEEN));
        rankingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, Constants.FIFTEEN));
        rankingTable.setRowHeight(Constants.TWENTYFIVE);
        container.add(new JScrollPane(rankingTable), BorderLayout.CENTER);

        // Error label
        backButton = new JButton("Back to Main Memu");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Constants.THEME_COLOR);
        backButton.setFont(new Font("Arial", Font.BOLD, Constants.TWENTY));
        container.add(backButton, BorderLayout.SOUTH);

        // Add ActionListener to navigate back to the main menu
        backButton.addActionListener(e -> {
            if (navigationManager != null) {
                navigationManager.showMainView();
            }
            else {
                JOptionPane.showMessageDialog(this, "Navigation Manager not initialized.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Window settings
        setSize(Constants.EIGHT_HUNDRED, Constants.SIX_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Constants.FOUR_HUNDRED, Constants.TWO_HUNDRED);
    }

    /**
     * Sets the LoginController for this view.
     *
     * @param rankingController The LoginController instance.
     */
    public void setRankingController(RankingController rankingController) {
        this.rankingController = rankingController;
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
     * Displays the leaderboard in the UI.
     *
     * @param playerNames   A list of player names.
     * @param scores        A list of player scores.
     * @param daysSurvived  A list of days survived by each player.
     * @param statuses      A list of statuses indicating if each player won or lost.
     */
    @Override
    public void displayRankings(List<String> playerNames, List<Integer> scores,
                                List<Integer> daysSurvived, List<String> statuses) {
        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Populate the table with new data
        for (int i = 0; i < playerNames.size(); i++) {
            tableModel.addRow(new Object[]{
                // Rank
                i + 1,
                // Player Name
                playerNames.get(i),
                // Score
                scores.get(i),
                // Days Survived
                daysSurvived.get(i),
                // Status (Won/Lost)
                statuses.get(i),
            });
        }
    }

    /**
     * Displays an error message in the UI.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Renders the ranking view window.
     */
    public void render() {
        setVisible(true);
        if (rankingController != null) {
            // Fetch rankings after the controller is set
            rankingController.handleRanking(Constants.TEN);
        }
    }

    public void disrender() {
        setVisible(false);
    }

    public static void main(String[] args) {
        try {
            new JsonApplication("PlayerFile", "PlayerFile", "RankingFile");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
