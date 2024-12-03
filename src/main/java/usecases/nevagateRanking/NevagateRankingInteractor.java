package usecases.nevagateRanking;

/**
 * The interactor for the NavigateRanking use case.
 * Implements the business logic for navigating to the ranking page.
 */
public class NevagateRankingInteractor implements NevagateRankingInputBoundary {
    private final NevagateRankingOutputBoundary outputBoundary;

    /**
     * Constructs a NevagateRankingInteractor instance.
     *
     * @param outputBoundary The output boundary for presenting the navigation results.
     */
    public NevagateRankingInteractor(NevagateRankingOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the navigation to the ranking page by invoking the output boundary.
     *
     * @param inputData The input data containing navigation context information.
     */
    @Override
    public void execute(NevagateRankingInputData inputData) {
        // Prepare output data
        final NevagateRankingOutputData outputData = new NevagateRankingOutputData("Navigating to Ranking");
        outputBoundary.present(outputData);
    }
}
