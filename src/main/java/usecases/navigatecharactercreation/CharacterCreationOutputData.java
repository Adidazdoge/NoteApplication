package usecases.navigatecharactercreation;

/**
 * Output data for character creation.
 */
public class CharacterCreationOutputData {
    private final String message;

    public CharacterCreationOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
