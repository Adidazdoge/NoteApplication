package usecases.endprocesshorde;

/**
 * Output data, consist of final outcome description, player's score.
 */
public class HordeOutputData {
    private int score;
    private String description;

    public HordeOutputData(int score, String description) {
        this.score = score;
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }
}
