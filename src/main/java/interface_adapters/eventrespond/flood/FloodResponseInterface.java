package interface_adapters.eventrespond.flood;

/**
 * Interface for updating the UI for a Flood event.
 */
public interface FloodResponseInterface {
    void updateUiResponse(String message);  // Update the UI with the event's message

    void failureResponse(String errorMessage);  // Handle failure response
}
