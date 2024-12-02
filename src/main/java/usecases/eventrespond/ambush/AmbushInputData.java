package usecases.eventrespond.ambush;

/**
 * Input data for the Ambush event, representing the player's choice.
 */
public class AmbushInputData {
    private final int choice;

    public AmbushInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
