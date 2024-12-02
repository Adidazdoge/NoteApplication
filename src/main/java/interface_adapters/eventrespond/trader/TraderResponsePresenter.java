package interface_adapters.eventrespond.trader;

import usecases.eventrespond.trader.TraderOutputBoundary;
import usecases.eventrespond.trader.TraderOutputData;

/**
 * Presenter for handling the response output of a Trader event.
 */
public class TraderResponsePresenter implements TraderOutputBoundary {
    private final TraderResponseInterface view;

    public TraderResponsePresenter(TraderResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(TraderOutputData outputData) {
        // Update the UI with the message from TraderOutputData
        view.updateUiResponse(outputData.getMessage());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);  // Handle failure case
    }
}
