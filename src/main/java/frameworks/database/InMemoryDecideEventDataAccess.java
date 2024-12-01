package frameworks.database;

import java.util.ArrayList;

import entities.Event;
import entities.EventsDecided;
import entities.Location;
import usecases.eventdecide.DecideEventDataAccessInterface;

/**
 * In-Memory implementation of DecideEventDataAccessInterface.
 */
public class InMemoryDecideEventDataAccess implements DecideEventDataAccessInterface {
    private final ArrayList<Event> events;
    private final Location currentLocation;
    private final EventsDecided currentEventsDecided;

    public InMemoryDecideEventDataAccess(ArrayList<Event> events,
                                         Location currentLocation, EventsDecided currentEventsDecided) {
        this.events = events;
        this.currentLocation = currentLocation;
        this.currentEventsDecided = currentEventsDecided;
    }

    @Override
    public ArrayList<Event> getEvents() {
        // Return a copy to ensure immutability
        return new ArrayList<>(events);
    }

    @Override
    public void setEvents(ArrayList<Event> events) {
        currentEventsDecided.setEvents(events);
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }
}
