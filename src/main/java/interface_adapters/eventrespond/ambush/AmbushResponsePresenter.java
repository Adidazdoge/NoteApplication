package interface_adapters.eventrespond.ambush;

import usecases.eventrespond.ambush.AmbushOutputBoundary;
import usecases.eventrespond.ambush.AmbushOutputData;

/**
 * Presenter for handling the response output of an Ambush event.
 */
public class AmbushResponsePresenter implements AmbushOutputBoundary {
    private final AmbushResponseInterface view;

    public AmbushResponsePresenter(AmbushResponseInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(AmbushOutputData outputData) {
        // Pass the message and relevant details to the view
        view.updateUiResponse(outputData.getMessage());  // Updated method based on new event-specific data
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureResponse(errorMessage);  // Handle failure case
    }
}
