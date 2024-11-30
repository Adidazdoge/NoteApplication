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

    /**
     * Constructs a new RankingEntry with the specified attributes.
     *
     * @param name The player's username.
     * @param score The player's score.
     * @param daysSurvived The number of days the player survived.
     * @param won Whether the player won the game.
     */
    public PlayerInfo(String name, int score, int daysSurvived, boolean won) {
        this.name = name;
        this.score = score;
        this.daysSurvived = daysSurvived;
        this.won = won;
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
}
