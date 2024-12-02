package interface_adapters.eventrespond.survivor;

import usecases.eventrespond.survivor.SurvivorEventInteractor;
import usecases.eventrespond.shared.RespondInputData;

/**
 * Controller for handling responses to a Survivor event.
 */
public class SurvivorResponseController {
    private final SurvivorEventInteractor interactor;

    public SurvivorResponseController(SurvivorEventInteractor interactor) {
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
