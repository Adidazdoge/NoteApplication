package usecases.eventrespond.blizzard;

/**
 * Input data for the Blizzard event, representing the player's choice.
 */
public class BlizzardInputData {
    private final int choice;

    public BlizzardInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
