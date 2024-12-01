package usecases.eventinitialize;

import java.util.Map;

/**
 * The return data from interact, contain the description of the event, and choices.
 */
public class EventInitializeOutputData {
    private String description;
    private Map<Integer, String> choices;

    public EventInitializeOutputData(String description, Map<Integer, String> choices) {
        this.description = description;
        this.choices = choices;
    }

    /**
     * Get the choice for key input.
     * @param key key, in number
     * @return String of the choice representation.
     */
    public String getChoice(int key) {
        return choices.get(key);
    }

    public String getDescription() {
        return description;
    }

    public Map<Integer, String> getChoices() {
        return choices;
    }
}
