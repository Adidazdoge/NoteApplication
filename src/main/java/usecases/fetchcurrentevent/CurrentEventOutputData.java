package usecases.fetchcurrentevent;

import entities.Event;

/**
 * Not needed, nothing to present to UI.
 */
public class CurrentEventOutputData {
    private Event currentEvent;

    public CurrentEventOutputData(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }
}
