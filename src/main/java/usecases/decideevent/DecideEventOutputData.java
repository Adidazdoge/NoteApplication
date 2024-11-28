package usecases.decideevent;

import java.util.ArrayList;

import entities.Event;

/**
 * Return the list of event decided, and waiting to be carried out on the following day.
 * Usually, is a empty list, but as we add more event, is expected to be more.
 */
public class DecideEventOutputData {
    private ArrayList<Event> events;

    public DecideEventOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
