package entities;

/**
 * Playerinfo during the game.
 */
public class PlayerInfo {
    // Player's username
    private final String name;
    // Player's score
    private int score;
    // Number of days the player survived
    private int daysSurvived;
    // Whether the player won the game
    private boolean won;
    private int actionPoint;

    /**
     * Constructs a new RankingEntry with the specified attributes.
     *
     * @param name The player's username.
     */

    public PlayerInfo(String name) {
        this.name = name;
        this.score = 0;
        this.daysSurvived = 1;
        this.won = false;
        this.actionPoint = EntityConstants.STARTERACTIONPOINT;
    }

    public PlayerInfo(String name, int score, int daysSurvived, boolean won, int actionPoint) {
        this.name = name;
        this.score = score;
        this.daysSurvived = daysSurvived;
        this.won = won;
        this.actionPoint = actionPoint;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDaysSurvived() {
        return daysSurvived;
    }

    public void setDaysSurvived(int daysSurvived) {
        this.daysSurvived = daysSurvived;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }
}
