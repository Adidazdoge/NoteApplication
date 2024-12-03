package usecases.gamelosedetecter;

/**
 * Display brief description, how and why you lose.
 */
public class LoseOutputData {
    private String losedescription;
    private int score;

    public LoseOutputData(String losedescription, int score) {
        this.losedescription = losedescription;
        this.score = score;
    }

    public String getLosedescription() {
        return losedescription;
    }

    public int getScore() {
        return score;
    }
}
