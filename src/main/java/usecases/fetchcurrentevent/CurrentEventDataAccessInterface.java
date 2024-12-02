package usecases.fetchcurrentevent;

import entities.Event;

/**
 * Current event data access.
 */
public interface CurrentEventDataAccessInterface {
    Event getEvent();
}
