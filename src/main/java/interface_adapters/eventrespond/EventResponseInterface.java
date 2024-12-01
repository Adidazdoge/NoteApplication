package interface_adapters.eventrespond;

/**
 * Interface for communicating with the view in a decoupled manner for event response use case.
 */
public interface EventResponseInterface {

    /**
     * Update the event description in the UI.
     * @param description The event description to display.
     */
    void updateEventDescription(String description);

    /**
     * Update the UI with the success message from the interactor.
     * @param message The success message.
     */
    void updateUiResponse(String message);

    /**
     * Display an error message if the event response fails.
     * @param errorMessage The error message to display.
     */
    void failureResponse(String errorMessage);
}
