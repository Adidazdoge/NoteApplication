package usecases.eventinitialize;

import java.util.Map;

/**
 * The return data from interact, contain the description of the event, and choices.
 */
public class EventInitializeOutputData {
    private String description;
    private Map<Integer, String> choices;

    EventInitializeOutputData(String description, Map<Integer, String> choices) {
        this.description = description;
        this.choices = choices;
    }

    private String getChoice(int key) {
        return choices.get(key);
    }

    private String getDescription() {
        return description;
    }

    private Map<Integer, String> getChoices() {
        return choices;
    }
}
