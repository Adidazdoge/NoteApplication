package interface_adapters.navigatecharactercreationview;

/**
 * Interface for updating the UI for character creation.
 */
public interface NavigateCharacterCreationInterface {
    void updateUiResponse(String message);

    void failureResponse(String errorMessage);
}
