package interface_adapters.eventrespond.blizzard;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for handling the response output of a Blizzard event.
 */
public class BlizzardResponsePresenter implements RespondOutputBoundary {
    private final BlizzardResponseInterface view;

    public BlizzardResponsePresenter(BlizzardResponseInterface view) {
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
