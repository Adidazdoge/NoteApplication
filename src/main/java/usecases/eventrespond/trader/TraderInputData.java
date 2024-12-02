package usecases.eventrespond.trader;

/**
 * Input data for the Trader event, representing the player's choice.
 */
public class TraderInputData {
    private final int choice;

    public TraderInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
