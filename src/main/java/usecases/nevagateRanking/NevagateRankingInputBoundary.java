package usecases.nevagateRanking;

/**
 * The input boundary interface for the NavigateRanking use case.
 * Acts as an entry point for triggering the navigation logic to the ranking page.
 */
public interface NevagateRankingInputBoundary {
    /**
     * Executes the navigation to the ranking page.
     *
     * @param inputData The data required for navigation, such as context information.
     */
    void execute(NevagateRankingInputData inputData);
}
