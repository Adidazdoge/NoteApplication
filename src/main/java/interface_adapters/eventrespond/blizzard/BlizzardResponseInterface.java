package interface_adapters.eventrespond.blizzard;

/**
 * Interface for updating the UI for a Blizzard event.
 */
public interface BlizzardResponseInterface {
    void updateUiResponse(String message);

    void failureResponse(String errorMessage);
}
