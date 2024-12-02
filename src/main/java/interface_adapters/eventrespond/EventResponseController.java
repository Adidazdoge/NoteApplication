package interface_adapters.eventrespond;

import usecases.eventrespond.shared.RespondInputBoundary;
import usecases.eventrespond.shared.RespondInputData;

/**
 * Controller for handling event responses and delegating to the correct interactor.
 */
public class EventResponseController {
    private final RespondInputBoundary interactor;

    public EventResponseController(RespondInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the interactor by converting player input into input data.
     * @param choice The player's choice for the event response.
     */
    public void execute(int choice) {
        RespondInputData inputData = new RespondInputData(choice);
        interactor.execute(inputData);
    }
}
