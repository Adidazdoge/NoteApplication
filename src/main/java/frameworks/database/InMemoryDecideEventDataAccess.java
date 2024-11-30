package frameworks.database;

import java.util.ArrayList;

import entities.Event;
import entities.Location;
import usecases.eventdecide.DecideEventDataAccessInterface;

/**
 * In-Memory implementation of DecideEventDataAccessInterface.
 */
public class InMemoryDecideEventDataAccess implements DecideEventDataAccessInterface {
    private final ArrayList<Event> events;
    private final Location currentLocation;

    public InMemoryDecideEventDataAccess(ArrayList<Event> events, Location currentLocation) {
        this.events = events;
        this.currentLocation = currentLocation;
    }

    @Override
    public ArrayList<Event> getEvents() {
        // Return a copy to ensure immutability
        return new ArrayList<>(events);
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }
}
