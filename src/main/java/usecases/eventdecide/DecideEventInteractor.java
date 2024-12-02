package usecases.eventdecide;

import java.util.ArrayList;

import entities.Event;
import entities.Location;

/**
 * Event decider interactor, given list of events, process it, return list of events which will be carried out
 * though the day.
 */
public class DecideEventInteractor implements DecideEventInputBoundary {
    private final DecideEventDataAccessInterface dataaccessobject;
    private final DecideEventOutputBoundary outputboundary;

    public DecideEventInteractor(DecideEventDataAccessInterface dataaccessobject,
                                 DecideEventOutputBoundary outputboundary) {
        this.dataaccessobject = dataaccessobject;
        this.outputboundary = outputboundary;
    }

    @Override
    public void execute(DecideEventInputData inputdata) {
        final ArrayList<Event> events = dataaccessobject.getALLEvents();
        final ArrayList<Event> decidedEvents = new ArrayList<>();
        final Location location = dataaccessobject.getLocation();
        final String locationName = location.toString();
        final ArrayList<String> decidedEventNames = new ArrayList<>();
        for (Event event : events) {
            final ArrayList<String> occuringlocations = event.getOccuringlocation();
            final double probability = event.getprobability();
            final double randomValue = Math.random();

            if (randomValue < probability) {
                if (occuringlocations.contains(locationName)) {
                    decidedEvents.add(event);
                    decidedEventNames.add(event.toString());
                    // will only add if probability hits, and is one of the possible location which player is at.
                }
            }
        }
        dataaccessobject.setDecidedEvents(decidedEvents);
        final DecideEventOutputData outputdata = new DecideEventOutputData(decidedEventNames);
        outputboundary.prepareSuccessView(outputdata);
    }

}
