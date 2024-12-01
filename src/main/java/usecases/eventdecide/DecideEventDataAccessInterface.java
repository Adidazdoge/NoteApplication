package usecases.eventdecide;

import entities.Event;
import entities.Location;

import java.util.ArrayList;

/**
 * Event deciding data access interface, which it need all lists of type event to get each probability of event,
 * This usecase is responsible for deciding which event or events or no event happened on that day, and provide
 * basic description of the event, as well as choices. consider this usecase as the before of respond usecase.
 */
public interface DecideEventDataAccessInterface {

    /**
     * This method is responsible for get all event and their info.
     * @return List of all events type data.
     */
    ArrayList<Event> getEvents();

    /**
     * This method will set the decided events for that day.
     * @param events events decided.
     */
    void setEvents(ArrayList<Event> events);

    /**
     * Get the player's current location, because certain event will only happen in certain location, Iceland
     * blizzard etc.
     * @return Location of the player.
     */
    Location getLocation();
}
