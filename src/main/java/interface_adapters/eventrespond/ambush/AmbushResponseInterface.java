package interface_adapters.eventrespond.ambush;

/**
 * Interface for updating the UI for an Ambush event.
 */
public interface AmbushResponseInterface {
    void updateUiResponse(String message);

    void failureResponse(String errorMessage);
}
