package interface_adapters.gamenewday;

/**
 * Interface, act as communication between new day adaptior and view.
 */
public interface NewdayInterface {

    /**
     * Update the view by providing message to the textbox, update the 4 resource representation.
     * @param message message for what happened in a new day.
     * @param food update food change.
     * @param water update water change.
     * @param people update people change.
     * @param weapon update weapon change.
     */
    void updateUiNewday(String message, int food, int water, int people, int weapon);

    /**
     * If usecase failed, for example is already day 60 and this button was click, show reason why, don't change
     * anything else.
     * @param message error message.
     */
    void failureNewday(String message);
}
