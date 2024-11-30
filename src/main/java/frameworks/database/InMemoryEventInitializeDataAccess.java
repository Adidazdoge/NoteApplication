package frameworks.database;

import entities.Event;
import usecases.eventinitialize.EventInitializeDataAccessInterface;

/**
 * In-Memory implementation of EventInitializeDataAccessInterface.
 */
public class InMemoryEventInitializeDataAccess implements EventInitializeDataAccessInterface {
    private final Event currentEvent;

    public InMemoryEventInitializeDataAccess(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    @Override
    public Event getEvent() {
        if (currentEvent == null) {
            throw new IllegalStateException("No event is currently being processed.");
        }
        return currentEvent;
    }
}
