package interface_adapters.eventrespond.survivor;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for handling the response output of a Survivor event.
 */
public class SurvivorResponsePresenter implements RespondOutputBoundary {
    private final SurvivorResponseInterface view;

    public SurvivorResponsePresenter(SurvivorResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(RespondOutputData outputData) {
        view.updateUiResponse(outputData.getMessage());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);
    }
}
