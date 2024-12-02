package interface_adapters.eventrespond.trader;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for handling the response output of a Trader event.
 */
public class TraderResponsePresenter implements RespondOutputBoundary {
    private final TraderResponseInterface view;

    public TraderResponsePresenter(TraderResponseInterface view) {
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
