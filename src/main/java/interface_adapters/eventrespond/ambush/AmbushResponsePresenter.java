package interface_adapters.eventrespond.ambush;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for handling the response output of an Ambush event.
 */
public class AmbushResponsePresenter implements RespondOutputBoundary {
    private final AmbushResponseInterface view;

    public AmbushResponsePresenter(AmbushResponseInterface view) {
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
