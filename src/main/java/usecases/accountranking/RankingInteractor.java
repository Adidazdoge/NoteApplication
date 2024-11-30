package usecases.accountranking;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entities.PlayerRankingEntry;

/**
 * Use case interactor for the ranking use case.
 * Implements the core logic for sorting and presenting the player leaderboard.
 */
public class RankingInteractor implements RankingInputBoundary {
    private final RankingDataAccessInterface dataAccessInterface;
    private final RankingOutputBoundary outputBoundary;

    /**
     * Constructs a RankingInteractor with the specified data access interface and output boundary.
     *
     * @param dataAccessInterface The interface for accessing ranking data.
     * @param outputBoundary The output boundary for presenting the leaderboard results.
     */
    public RankingInteractor(RankingDataAccessInterface dataAccessInterface, RankingOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the ranking use case.
     * Retrieves the leaderboard data, sorts it based on the requirements,
     * and prepares the appropriate view for the top N players.
     *
     * @param inputData The input data containing the number of top players to retrieve.
     */
    @Override
    public void execute(RankingInputData inputData) {
        // Retrieve the unsorted list of players from the data access interface
        final List<PlayerRankingEntry> rankings = dataAccessInterface.getLeaderboard();

        if (rankings == null || rankings.isEmpty()) {
            outputBoundary.prepareFailureView("No players found in the leaderboard.");
            return;
        }

        // Sort the players first by `won` (true > false), then by `score` in descending order
        final List<PlayerRankingEntry> sortedRankings = rankings.stream()
                .sorted(Comparator.comparing(PlayerRankingEntry::isWon).reversed()
                        .thenComparing(Comparator.comparing(PlayerRankingEntry::getScore).reversed()))
                // Get only the top N players
                .limit(inputData.getTopN())
                .collect(Collectors.toList());

        // Prepare the sorted list as output data
        outputBoundary.prepareSuccessView(new RankingOutputData(sortedRankings));
    }
}
