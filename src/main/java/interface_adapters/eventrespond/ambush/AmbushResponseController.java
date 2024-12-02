package interface_adapters.eventrespond.ambush;

import usecases.eventrespond.ambush.AmbushEventInteractor;
import usecases.eventrespond.shared.RespondInputData;

/**
 * Controller for handling responses to an Ambush event.
 */
public class AmbushResponseController {
    private final AmbushEventInteractor interactor;

    public AmbushResponseController(AmbushEventInteractor interactor) {
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
