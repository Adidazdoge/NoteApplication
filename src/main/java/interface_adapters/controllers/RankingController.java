package interface_adapters.controllers;

import java.util.List;

import frameworks.view.RankingViewModel;
import interface_adapters.presenters.RankingPresenter;
import usecases.ranking.RankingRequest;
import usecases.ranking.RankingResponse;
import usecases.ranking.RankingUseCase;

/**
 * Handles user input for retrieving the ranking list.
 * Passes the input to the RankingUseCase and processes the output through the RankingPresenter.
 */
public class RankingController {
    private final RankingUseCase useCase;
    private final RankingPresenter presenter;

    /**
     * Constructs a new RankingController with the specified use case and presenter.
     *
     * @param useCase The use case to handle ranking logic.
     * @param presenter The presenter to process the response for the UI layer.
     */
    public RankingController(RankingUseCase useCase, RankingPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    /**
     * Handles a ranking request and returns a list of ViewModel objects for the UI layer.
     *
     * @param topN The number of top players to retrieve.
     * @return A list of RankingViewModel objects for the UI layer.
     */
    public List<RankingViewModel> handleRankingRequest(int topN) {
        final RankingRequest request = new RankingRequest(topN);
        final RankingResponse response = useCase.execute(request);
        // Convert response to ViewModel
        return presenter.present(response);
    }
}
