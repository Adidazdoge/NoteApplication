package interface_adapters.eventdecide;

import java.util.ArrayList;

import usecases.eventdecide.DecideEventOutputBoundary;
import usecases.eventdecide.DecideEventOutputData;

/**
 * Event decider presenter.
 */
public class EventDecidePresenter implements DecideEventOutputBoundary {
    private final EventDecideInterface view;

    public EventDecidePresenter(EventDecideInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(DecideEventOutputData outputData) {
        final ArrayList<String> events = outputData.getEvents();
        view.updateUiEventDecide(events);
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureEventDecide(errorMessage);
    }
}
