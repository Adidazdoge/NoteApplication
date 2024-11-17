package use_case.respond;

/**
 * Input data form the view/player side like the choice they choose in term os 12345...
 */
public class RespondInputData {
    private int choice;

    public RespondInputData(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
