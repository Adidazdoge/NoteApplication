package usecases.NevagateEventPage;

import entities.Event;
import java.util.ArrayList;

/**
 * Interactor, determine if player is able to press and go to event.
 * Only able to go when there is event. eventdecided is not empty.
 */
public class NevagateEventInteractor implements NevagateEventInputBoundary {
    private NevagateEventDataAccessInterface dataaccess;
    private NevagateEventOutputBoundary EventOutputBoundary;

    @Override
    public void execute(NevagateEventInputdata inputdata) {
        final ArrayList<Event> events = dataaccess.getEvents();
        if (events.size() > 0) {
            final NevagateEventOutputdata nevagateEventOutputdata = new NevagateEventOutputdata();
            EventOutputBoundary.nevagateEventPage(nevagateEventOutputdata);
        }
    }
}
