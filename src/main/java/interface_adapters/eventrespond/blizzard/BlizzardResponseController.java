package interface_adapters.eventrespond.blizzard;

import usecases.eventrespond.blizzard.BlizzardEventInteractor;
import usecases.eventrespond.blizzard.BlizzardInputData;

/**
 * Controller for handling responses to a Blizzard event.
 */
public class BlizzardResponseController {
    private final BlizzardEventInteractor interactor;

    public BlizzardResponseController(BlizzardEventInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the interactor by converting the player’s input into input data.
     * @param choice The player's choice for the event.
     */
    public void execute(int choice) {
        BlizzardInputData inputData = new BlizzardInputData(choice);  // Using BlizzardInputData now
        interactor.execute(inputData);
    }
}
