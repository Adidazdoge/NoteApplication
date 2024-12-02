package usecases.navigatecharactercreation;

/**
 * Character creation interactor.
 */
public class CharacterCreationInteractor implements CharacterCreationInputBoundary {
    private final CharacterCreationDataAccessInterface dataAccess;
    private final CharacterCreationOutputBoundary outputBoundary;

    public CharacterCreationInteractor(CharacterCreationDataAccessInterface dataAccess,
                                       CharacterCreationOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(CharacterCreationInputData inputData) {
        // Business logic to save character data
        dataAccess.saveCharacterData(inputData.getSocial(), inputData.getLuck(),
                inputData.getMobilization(), inputData.getThrift(), inputData.getGeneralship());

        // Notify presenter to update the view
        outputBoundary.prepareCharacterCreationView("Character creation successful!");
    }
}
