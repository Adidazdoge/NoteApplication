package interface_adapters.eventrespond.trader;

/**
 * Interface for updating the UI for a Trader event.
 */
public interface TraderResponseInterface {
    void updateUiResponse(String message);

    void failureResponse(String errorMessage);
}
