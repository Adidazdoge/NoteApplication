package interface_adapters.eventdecide;

import java.util.ArrayList;

/**
 * Interface of event decide.
 */
public interface EventDecideInterface {

    /**
     * Update the UI event decide "Event happened today:", or not event at all.
     * @param eventNames name of the events chose.
     */
    void updateUiEventDecide(ArrayList<String> eventNames);

    /**
     * In case something went wrong.
     * @param errorMessage error description.
     */
    void failureEventDecide(String errorMessage);
}
