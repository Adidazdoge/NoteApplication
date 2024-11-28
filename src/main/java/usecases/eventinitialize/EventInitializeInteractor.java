package usecases.eventinitialize;

import java.util.Map;

/**
 * Interactor of event initialize, which tranform the given event to return output data type of description,
 * and group of choices.
 */
public class EventInitializeInteractor implements EventInitializeInputBoundary {
    private final EventInitializeDataAccessInterface dataAccessObject;
    private final EventInitializeOutputBoundary outputBoundary;

    public EventInitializeInteractor(EventInitializeDataAccessInterface DataAccessObject,
                                     EventInitializeOutputBoundary OutputBoundary) {
        this.dataAccessObject = DataAccessObject;
        this.outputBoundary = OutputBoundary;
    }

    @Override
    public void execute(EventInitializeInputData inputdata) {
        final String description = dataAccessObject.getEvent().getdescription();
        final Map<Integer, String> choices = dataAccessObject.getEvent().getchoices();
        final EventInitializeOutputData outputData = new EventInitializeOutputData(description, choices);
        outputBoundary.prepareSuccessView(outputData);
    }
}
