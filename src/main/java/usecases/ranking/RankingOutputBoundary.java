package usecases.ranking;

/**
 * Output boundary for the ranking use case.
 * Defines methods for preparing success and failure views for leaderboard retrieval.
 */
public interface RankingOutputBoundary {
    /**
     * Prepares the view for successfully retrieved rankings.
     *
     * @param outputData The output data containing the leaderboard information.
     */
    void prepareSuccessView(RankingOutputData outputData);

    /**
     * Prepares the view for a failed ranking retrieval attempt.
     *
     * @param errorMessage The error message to display.
     */
    void prepareFailureView(String errorMessage);
}
