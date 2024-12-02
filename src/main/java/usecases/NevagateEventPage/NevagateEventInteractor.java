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

    public NevagateEventInteractor(NevagateEventDataAccessInterface dataaccess,
                                   NevagateEventOutputBoundary EventOutputBoundary) {
        this.dataaccess = dataaccess;
        this.EventOutputBoundary = EventOutputBoundary;
    }

    @Override
    public void execute(NevagateEventInputdata inputdata) {
        final ArrayList<Event> events = dataaccess.getDecidedEvents();
        if (events.size() > 0) {
            final NevagateEventOutputdata nevagateEventOutputdata = new NevagateEventOutputdata();
            EventOutputBoundary.nevagateEventPage(nevagateEventOutputdata);
        }
        else {
            EventOutputBoundary.nevagateEventFailed("Peaceful day, no event happened.");
        }
    }
}
