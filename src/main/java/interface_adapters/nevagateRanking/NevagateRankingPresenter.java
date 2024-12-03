package interface_adapters.nevagateRanking;

import interface_adapters.NavigationManagerJson;
import usecases.nevagateRanking.NevagateRankingOutputBoundary;
import usecases.nevagateRanking.NevagateRankingOutputData;

/**
 * Presenter for the NavigateRanking use case.
 * Handles the navigation logic and delegates view updates to the NavigationManager.
 */
public class NevagateRankingPresenter implements NevagateRankingOutputBoundary {
    private final NavigationManagerJson navigationManager;

    /**
     * Constructs a NevagateRankingPresenter instance.
     *
     * @param navigationManager The navigation manager responsible for view transitions.
     */
    public NevagateRankingPresenter(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
    }

    /**
     * Presents the navigation results by invoking the appropriate view transition.
     *
     * @param outputData The output data containing the navigation result message.
     */
    @Override
    public void present(NevagateRankingOutputData outputData) {
        // Navigate to the Ranking view
        navigationManager.showRankingView();
    }
}
