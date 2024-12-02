package interface_adapters.eventrespond;

/**
 * Interface for updating the event response UI.
 */
public interface EventResponseInterface {

    /**
     * Update the UI with the result of a successful event response.
     * @param message The success message to display.
     */
    void updateUiResponse(String message);

    /**
     * Update the UI with an error message if the response fails.
     * @param errorMessage The error message to display.
     */
    void failureResponse(String errorMessage);
}
