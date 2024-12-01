package interface_adapters.rankinglist;

import java.util.List;
import java.util.stream.Collectors;

import entities.PlayerRankingEntry;
import usecases.accountranking.RankingOutputBoundary;
import usecases.accountranking.RankingOutputData;

/**
 * The RankingPresenter class is responsible for converting the output data
 * from the Ranking use case into a format suitable for the user interface.
 * It interacts with the view via the RankingInterface.
 */
public class RankingPresenter implements RankingOutputBoundary {
    // The view that will display the ranking information
    private final RankingInterface view;

    /**
     * Constructs a new RankingPresenter with the specified view.
     *
     * @param view The view that will display the leaderboard and errors.
     */
    public RankingPresenter(RankingInterface view) {
        this.view = view;
    }

    /**
     * Prepares the view for a successful leaderboard retrieval.
     * Separates PlayerRankingEntry objects into individual data lists.
     *
     * @param outputData The output data containing the sorted leaderboard information.
     */
    @Override
    public void prepareSuccessView(RankingOutputData outputData) {
        // Extract individual components of PlayerRankingEntry
        final List<String> playerNames = outputData.getRankings().stream()
                .map(PlayerRankingEntry::getName)
                .collect(Collectors.toList());
        final List<Integer> scores = outputData.getRankings().stream()
                .map(PlayerRankingEntry::getScore)
                .collect(Collectors.toList());
        final List<Integer> daysSurvived = outputData.getRankings().stream()
                .map(PlayerRankingEntry::getDaysSurvived)
                .collect(Collectors.toList());
        final List<String> statuses = outputData.getRankings().stream()
                .map(entry -> entry.isWon() ? "Won" : "Lost")
                .collect(Collectors.toList());

        // Update the view with separate lists
        view.displayRankings(playerNames, scores, daysSurvived, statuses);
    }

    /**
     * Prepares the view for a failed leaderboard retrieval attempt.
     * Notifies the view of the error.
     *
     * @param errorMessage The error message indicating why the retrieval failed.
     */
    @Override
    public void prepareFailureView(String errorMessage) {
        // Notify the view of the failure
        view.displayError(errorMessage);
    }
}

