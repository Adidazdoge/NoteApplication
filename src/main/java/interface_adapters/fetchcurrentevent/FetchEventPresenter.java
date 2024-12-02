package interface_adapters.fetchcurrentevent;

import usecases.fetchcurrentevent.CurrentEventOutputBoundary;
import usecases.fetchcurrentevent.CurrentEventOutputData;

public class FetchEventPresenter implements CurrentEventOutputBoundary {
    private FetchEventInterface view;

    public FetchEventPresenter(FetchEventInterface view) {
        this.view = view;
    }

    @Override
    public void presentcurrentEvent(CurrentEventOutputData currentEventOutputData) {
        view.setEventName(currentEventOutputData.getCurrentEvent().toString());
    }
}
