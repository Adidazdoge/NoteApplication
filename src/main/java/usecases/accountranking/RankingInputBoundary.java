package usecases.accountranking;

/**
 * Input boundary for the ranking use case.
 * Defines the interface for retrieving and sorting the player leaderboard.
 */
public interface RankingInputBoundary {
    /**
     * Executes the ranking use case with the provided input data.
     *
     * @param inputData The input data containing the number of top players to retrieve.
     */
    void execute(RankingInputData inputData);
}
