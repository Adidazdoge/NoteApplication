package interface_adapters.eventinitializer;

import usecases.eventinitialize.EventInitializeOutputBoundary;
import usecases.eventinitialize.EventInitializeOutputData;

/**
 * Event Initialize presenter.
 */
public class EventInitializerPresenter implements EventInitializeOutputBoundary {
    private EventInitializerInterface view;

    public EventInitializerPresenter(EventInitializerInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(EventInitializeOutputData outputData) {
        final String description = outputData.getDescription();
        final String choice1 = outputData.getChoice(1);
        final String choice2 = outputData.getChoice(2);
        final String choice3 = outputData.getChoice(3);
        view.updateUiEventInitializer(description, choice1, choice2, choice3);
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureEventInitializer(errorMessage);
    }
}
