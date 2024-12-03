package interface_adapters.nevagateRanking;

import usecases.nevagateRanking.NevagateRankingInputBoundary;
import usecases.nevagateRanking.NevagateRankingInputData;

/**
 * Controller for the NavigateRanking use case.
 * Acts as a bridge between the user interface and the interactor.
 */
public class NevagateRankingController {
    private final NevagateRankingInputBoundary interactor;

    /**
     * Constructs a NevagateRankingController instance.
     *
     * @param interactor The interactor responsible for processing the navigation logic.
     */
    public NevagateRankingController(NevagateRankingInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Triggers the navigation to the ranking page by passing input data to the interactor.
     *
     * @param context A string representing the navigation context (e.g., "Navigate to Ranking").
     */
    public void navigateToRanking(String context) {
        final NevagateRankingInputData inputData = new NevagateRankingInputData(context);
        interactor.execute(inputData);
    }
}
