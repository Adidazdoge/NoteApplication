package interface_adapters.fetchcurrentevent;

import usecases.fetchcurrentevent.CurrentEventInputBoundary;
import usecases.fetchcurrentevent.CurrentEventInputData;

public class FetchEventController {
    private CurrentEventInputBoundary interactor;

    public FetchEventController(CurrentEventInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute(new CurrentEventInputData());
    }
}
