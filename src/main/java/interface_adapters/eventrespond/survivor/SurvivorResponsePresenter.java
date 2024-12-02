package interface_adapters.eventrespond.survivor;

import usecases.eventrespond.survivor.SurvivorOutputBoundary;
import usecases.eventrespond.survivor.SurvivorOutputData;

/**
 * Presenter for handling the response output of a Survivor event.
 */
public class SurvivorResponsePresenter implements SurvivorOutputBoundary {
    private final SurvivorResponseInterface view;

    public SurvivorResponsePresenter(SurvivorResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(SurvivorOutputData outputData) {
        // Update the UI with the message from SurvivorOutputData
        view.updateUiResponse(outputData.getMessage());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);  // Handle failure case
    }
}
