package interface_adapters.navigatecharactercreationview;

import usecases.navigatecharactercreation.CharacterCreationInputBoundary;
import usecases.navigatecharactercreation.CharacterCreationInputData;

/**
 * Controller for handling responses to character creation.
 */
public class NavigateCharacterCreationController {
    private final CharacterCreationInputBoundary interactor;

    public NavigateCharacterCreationController(CharacterCreationInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the interactor by converting the player’s input into input data.
     * @param choice The player's choice for the event.
     */
    public void execute(int social, int luck, int mobilization, int thrift, int generalship) {
        CharacterCreationInputData inputData = new CharacterCreationInputData(social, luck, mobilization, thrift, generalship);
        interactor.execute(inputData);
    }
}
