package usecases.eventrespond.survivor;

/**
 * Input data for the Survivor event, representing the player's choice.
 */
public class SurvivorInputData {
    private final int choice;

    public SurvivorInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
