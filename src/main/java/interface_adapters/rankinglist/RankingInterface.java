package interface_adapters.rankinglist;

import java.util.List;

/**
 * The RankingInterface defines the methods that a view should implement
 * to display the leaderboard and handle any errors.
 */
public interface RankingInterface {

    /**
     * Updates the UI with the sorted leaderboard entries as separate data points.
     *
     * @param playerNames A list of player names.
     * @param scores A list of player scores.
     * @param daysSurvived A list of the days survived by each player.
     * @param statuses A list of statuses indicating if each player won or lost.
     */
    void displayRankings(List<String> playerNames, List<Integer> scores,
                         List<Integer> daysSurvived, List<String> statuses);

    /**
     * Displays an error message in case of a failure in leaderboard retrieval.
     *
     * @param errorMessage The error message to display.
     */
    void displayError(String errorMessage);
}
