package usecases.eventinitialize;

import entities.Event;

/**
 * DAI of event initiaizer, assume this event is been chosen by event decider, this use case is for transform event
 * to datatype which view needs, such as getting the choices, descriptions.
 */
public interface EventInitializeDataAccessInterface {
    /**
     * Get the event that's processing now.
     * @return The event.
     */
    Event getEvent();

}
