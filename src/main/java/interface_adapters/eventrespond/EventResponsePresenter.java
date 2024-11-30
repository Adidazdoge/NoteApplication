package interface_adapters.eventrespond;

import usecases.eventrespond.shared.RespondOutputBoundary;
import usecases.eventrespond.shared.RespondOutputData;

/**
 * Presenter for the event response use case.
 * Implements the RespondOutputBoundary interface to handle the use case output.
 */
public class EventResponsePresenter implements RespondOutputBoundary {
    private final EventResponseInterface view;

    public EventResponsePresenter(EventResponseInterface view) {
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
