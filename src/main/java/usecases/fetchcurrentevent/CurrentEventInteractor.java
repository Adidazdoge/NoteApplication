package usecases.fetchcurrentevent;

import entities.Event;

/**
 * Current event interactor.
 */
public class CurrentEventInteractor implements CurrentEventInputBoundary {
    private CurrentEventDataAccessInterface dataaccess;
    private CurrentEventOutputBoundary outputBoundary;

    public CurrentEventInteractor(CurrentEventDataAccessInterface dataaccess,
                                  CurrentEventOutputBoundary outputBoundary) {
        this.dataaccess = dataaccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(CurrentEventInputData inputData) {
        final Event event = dataaccess.getEvent();
        outputBoundary.presentcurrentEvent(new CurrentEventOutputData(event));
    }
}
