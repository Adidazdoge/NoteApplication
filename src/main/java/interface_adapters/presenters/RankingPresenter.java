package interface_adapters.presenters;

import java.util.List;

import entities.PlayerRankingEntry;
import usecases.ranking.RankingOutputBoundary;
import usecases.ranking.RankingOutputData;

/**
 * The RankingPresenter class is responsible for formatting the output data
 * from the Ranking use case into a format suitable for the user interface.
 * It returns a sorted list of PlayerRankingEntry objects for further use by the UI.
 */
public class RankingPresenter implements RankingOutputBoundary {
    // Stores the sorted leaderboard
    private List<PlayerRankingEntry> sortedRankings;

    /**
     * Prepares the view for a successful leaderboard retrieval.
     * Stores the sorted PlayerRankingEntry objects for retrieval by the UI.
     *
     * @param outputData The output data containing the leaderboard information.
     */
    @Override
    public void prepareSuccessView(RankingOutputData outputData) {
        // Store the sorted list of PlayerRankingEntry objects
        this.sortedRankings = outputData.getRankings();
    }

    /**
     * Prepares the view for a failed leaderboard retrieval attempt.
     * Clears the rankings list and logs an error.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void prepareFailureView(String errorMessage) {
        this.sortedRankings = List.of(); // Clear the rankings list on failure
        System.err.println("Error retrieving rankings: " + errorMessage);
    }

    /**
     * Retrieves the sorted list of PlayerRankingEntry objects for display.
     *
     * @return A list of sorted PlayerRankingEntry objects or an empty list if retrieval failed.
     */
    public List<PlayerRankingEntry> getSortedRankings() {
        return sortedRankings;
    }
}

