package interface_adapters.eventrespond.flood;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for handling the response output of a Flood event.
 */
public class FloodResponsePresenter implements RespondOutputBoundary {
    private final FloodResponseInterface view;

    public FloodResponsePresenter(FloodResponseInterface view) {
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
