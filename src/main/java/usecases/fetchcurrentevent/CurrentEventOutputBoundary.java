package usecases.fetchcurrentevent;

import entities.Event;

/**
 * Not needed as this is simply just passing the current event.
 */
public interface CurrentEventOutputBoundary {
    void presentcurrentEvent(CurrentEventOutputData currentEventOutputData);
}
