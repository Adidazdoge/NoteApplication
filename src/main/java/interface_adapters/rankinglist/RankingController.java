package interface_adapters.rankinglist;

import usecases.accountranking.RankingInputBoundary;
import usecases.accountranking.RankingInputData;

/**
 * The RankingController class is responsible for handling user input from the UI
 * and invoking the Ranking use case to process the leaderboard retrieval logic.
 * It acts as the bridge between the user interface and the application's business logic.
 */
public class RankingController {
    private final RankingInputBoundary rankingUseCase;

    /**
     * Constructs a new RankingController with the specified RankingInputBoundary.
     *
     * @param rankingUseCase The input boundary instance responsible for processing ranking requests.
     */
    public RankingController(RankingInputBoundary rankingUseCase) {
        this.rankingUseCase = rankingUseCase;
    }

    /**
     * Handles the leaderboard retrieval process by creating a RankingInputData object
     * with the specified number of top players to retrieve, and passing it to the Ranking use case.
     *
     * @param topN The number of top players to retrieve from the leaderboard.
     */
    public void handleRanking(int topN) {
        // Create input data object for the use case
        final RankingInputData inputData = new RankingInputData(topN);

        // Invoke the use case with the input data
        rankingUseCase.execute(inputData);
    }
}
