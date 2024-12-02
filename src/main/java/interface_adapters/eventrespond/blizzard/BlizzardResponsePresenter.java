package interface_adapters.eventrespond.blizzard;

import usecases.eventrespond.blizzard.BlizzardOutputBoundary;
import usecases.eventrespond.blizzard.BlizzardOutputData;

/**
 * Presenter for handling the response output of a Blizzard event.
 */
public class BlizzardResponsePresenter implements BlizzardOutputBoundary {
    private final BlizzardResponseInterface view;

    public BlizzardResponsePresenter(BlizzardResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(BlizzardOutputData outputData) {
        // Pass the message and relevant details to the view
        view.updateUiResponse(outputData.getMessage());  // Updated method based on new event-specific data
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);  // Handle failure case
    }
}
