package interface_adapters.eventrespond.trader;

/**
 * Interface for updating the UI for a Trader event.
 */
public interface TraderResponseInterface {
    void updateUiResponse(String message);  // Update the UI with the event's message

    void failureResponse(String errorMessage);  // Handle failure response
}
