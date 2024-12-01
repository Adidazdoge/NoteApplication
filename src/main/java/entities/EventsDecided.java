package entities;

import java.util.ArrayList;

/**
 * Events that has been decided and not yet processed by player.
 */
public class EventsDecided {
    private ArrayList<Event> events;

    public EventsDecided() {
        events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
