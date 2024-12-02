package interface_adapters.eventrespond.flood;

import usecases.eventrespond.flood.FloodEventInteractor;
import usecases.eventrespond.shared.RespondInputData;

/**
 * Controller for handling responses to a Flood event.
 */
public class FloodResponseController {
    private final FloodEventInteractor interactor;

    public FloodResponseController(FloodEventInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the interactor by converting the player’s input into input data.
     * @param choice The player's choice for the event.
     */
    public void execute(int choice) {
        RespondInputData inputData = new RespondInputData(choice);
        interactor.execute(inputData);
    }
}
