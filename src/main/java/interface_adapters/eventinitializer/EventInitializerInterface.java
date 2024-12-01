package interface_adapters.eventinitializer;

/**
 * Interface for presenter talking to view.
 */
public interface EventInitializerInterface {

    /**
     * Update the event page by update the description, and options for the buttons.
     * @param eventdescription Description of the event.
     * @param option1 option 1
     * @param option2 option 2
     * @param option3 option 3
     */
    void updateUiEventInitializer(String eventdescription, String option1, String option2, String option3);

    /**
     * Update the event page by saying no event at that day or something else is going on.
     * @param failmessage fail message.
     */
    void failureEventInitializer(String failmessage);
}
