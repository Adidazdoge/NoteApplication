package interface_adapters.eventrespond.flood;

import usecases.eventrespond.flood.FloodOutputBoundary;
import usecases.eventrespond.flood.FloodOutputData;

/**
 * Presenter for handling the response output of a Flood event.
 */
public class FloodResponsePresenter implements FloodOutputBoundary {
    private final FloodResponseInterface view;

    public FloodResponsePresenter(FloodResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(FloodOutputData outputData) {
        // Update the UI with the message from FloodOutputData
        view.updateUiResponse(outputData.getMessage());  // Updated to FloodOutputData's message
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);  // Handle failure case
    }
}
