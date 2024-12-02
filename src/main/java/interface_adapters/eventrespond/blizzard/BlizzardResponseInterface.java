package interface_adapters.eventrespond.blizzard;

/**
 * Interface for updating the UI for a Blizzard event.
 */
public interface BlizzardResponseInterface {
    void updateUiResponse(String message);  // Message to update UI with

    void failureResponse(String errorMessage);  // Handle failure cases
}
