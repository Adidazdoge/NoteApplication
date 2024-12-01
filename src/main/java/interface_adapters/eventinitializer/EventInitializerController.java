package interface_adapters.eventinitializer;

import usecases.eventinitialize.EventInitializeInputBoundary;
import usecases.eventinitialize.EventInitializeInputData;

/**
 * Event initializer controller.
 */
public class EventInitializerController {
    private EventInitializeInputBoundary interactor;

    public EventInitializerController(EventInitializeInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute with input data provided, not needed.
     */
    public void execute() {
        final EventInitializeInputData inputData = new EventInitializeInputData();
        interactor.execute(inputData);
    }
}
