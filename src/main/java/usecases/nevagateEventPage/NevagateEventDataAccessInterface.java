package usecases.nevagateEventPage;

import entities.Event;

import java.util.ArrayList;

/**
 * Not needed.
 */
public interface NevagateEventDataAccessInterface {
    /**
     * Get all events decided for that day.
     * @return events.
     */
    ArrayList<Event> getDecidedEvents();
}
