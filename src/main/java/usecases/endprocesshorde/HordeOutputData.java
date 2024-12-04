package usecases.endprocesshorde;

/**
 * Output data, consist of final outcome description, player's score.
 */
public class HordeOutputData {
    private int points;
    private String introduction;

    public HordeOutputData(int Score, String Description) {
        this.points = Score;
        this.introduction = Description;
    }

    public int getPoints() {

        return points;
    }

    public String getIntroduction() {

        return introduction;
    }
}
