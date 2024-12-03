package usecases.nevagateRanking;

/**
 * The output boundary interface for the NavigateRanking use case.
 * Defines the contract for presenting the results of the navigation process.
 */
public interface NevagateRankingOutputBoundary {
    /**
     * Presents the output data for the navigation logic.
     *
     * @param outputData The output data containing navigation results.
     */
    void present(NevagateRankingOutputData outputData);
}
