package interface_adapters.eventrespond;

/**
 * Interface for communicating with the view in a decoupled manner for event response use case.
 */
public interface EventResponseInterface {

    /**
     * Update the UI after a successful response to an event.
     * @param message The success message to display.
     */
    void updateUiResponse(String message);

    /**
     * Display an error message if the event response fails.
     * @param errorMessage The error message to display.
     */
    void failureResponse(String errorMessage);
}
