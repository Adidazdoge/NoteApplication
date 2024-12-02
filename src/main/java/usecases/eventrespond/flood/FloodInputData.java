package usecases.eventrespond.flood;

/**
 * Input data for the Flood event, representing the player's choice.
 */
public class FloodInputData {
    private final int choice;

    public FloodInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
