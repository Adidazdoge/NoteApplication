package interface_adapters.eventrespond.survivor;

/**
 * Interface for updating the UI for a Survivor event.
 */
public interface SurvivorResponseInterface {
    void updateUiResponse(String message);

    void failureResponse(String errorMessage);
}
