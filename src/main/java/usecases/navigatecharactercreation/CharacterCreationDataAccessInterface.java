package usecases.charactercreation;

/**
 * Data access interface for character creation.
 */
public interface CharacterCreationDataAccessInterface {
    void saveCharacterData(int social, int luck, int mobilization, int thrift, int generalship);
}
