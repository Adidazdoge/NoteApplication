package interface_adapters.nevagateevent;

import interface_adapters.NavigationManager;
import usecases.nevagateEventPage.NevagateEventOutputBoundary;
import usecases.nevagateEventPage.NevagateEventOutputdata;

public class NevagateEventPresenter implements NevagateEventOutputBoundary {
    private final NavigationManager navigationManager;
    private final NevagateEventInterface view;

    public NevagateEventPresenter(NavigationManager navigationManager, NevagateEventInterface view) {
        this.navigationManager = navigationManager;
        this.view = view;
    }

    @Override
    public void nevagateEventPage(NevagateEventOutputdata outputdata) {
        navigationManager.showEventView();
    }

    @Override
    public void nevagateEventFailed(String failmessage) {
        view.failureNevagateEvent(failmessage);
    }
}
