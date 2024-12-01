package usecases.eventdecide;

import java.util.ArrayList;

import entities.Event;

/**
 * Return the list of event decided, and waiting to be carried out on the following day.
 * Usually, is a empty list, but as we add more event, is expected to be more.
 */
public class DecideEventOutputData {
    private ArrayList<String> events;

    public DecideEventOutputData(ArrayList<String> events) {
        this.events = events;
    }

    public ArrayList<String> getEvents() {
        return events;
    }
}
