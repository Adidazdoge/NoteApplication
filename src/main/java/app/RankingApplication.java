package app;

import java.io.IOException;

import frameworks.database.JsonRankingDataAccess;
import interface_adapters.rankinglist.RankingController;
import interface_adapters.rankinglist.RankingInterface;
import interface_adapters.rankinglist.RankingPresenter;
import usecases.accountranking.RankingInputBoundary;
import usecases.accountranking.RankingInteractor;
import usecases.accountranking.RankingOutputBoundary;

/**
 * Application class for Ranking Use Case.
 * Responsible for assembling and initializing the components for the ranking feature.
 */
public class RankingApplication {
    private static String filePath = "RankingFile";

    /**
     * Initializes and assembles the components for the Ranking use case.
     *
     * @param rankingView The interface representing the view layer of the ranking feature.
     * @return A RankingController instance that is connected to the use case.
     * @throws IOException if there is an issue accessing ranking data files.
     */
    public static RankingController initializeRanking(RankingInterface rankingView) throws IOException {
        // Data access layer
        final JsonRankingDataAccess rankingDataAccess = new JsonRankingDataAccess(filePath);

        // Presenter
        final RankingPresenter rankingPresenter = new RankingPresenter(rankingView);

        // Output boundary is the presenter
        final RankingOutputBoundary outputBoundary = rankingPresenter;

        // Use case interactor
        final RankingInputBoundary rankingInteractor = new RankingInteractor(rankingDataAccess, outputBoundary);

        // Controller
        return new RankingController(rankingInteractor);
    }
}
