package interface_adapters.eventrespond.survivor;

/**
 * Interface for updating the UI for a Survivor event.
 */
public interface SurvivorResponseInterface {
    void updateUiResponse(String message);  // Update the UI with the event's message

    void failureResponse(String errorMessage);  // Handle failure response
}
