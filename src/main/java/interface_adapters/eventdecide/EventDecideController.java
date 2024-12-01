package interface_adapters.eventdecide;

import usecases.eventdecide.DecideEventInputBoundary;
import usecases.eventdecide.DecideEventInputData;

/**
 * Event deside controller
 */
public class EventDecideController {
    private DecideEventInputBoundary interactor;

    public EventDecideController(DecideEventInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute.
     */
    public void execute() {
        final DecideEventInputData inputData = new DecideEventInputData();
        interactor.execute(inputData);
    }
}
