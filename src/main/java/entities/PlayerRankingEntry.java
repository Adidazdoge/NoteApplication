package entities;

/**
 * Represents an entry in the ranking list.
 * Contains the player's name, score, days survived, and whether they won.
 */
public class PlayerRankingEntry {
    // Player's username
    private final String name;
    // Player's score
    private final int score;
    // Number of days the player survived
    private final int daysSurvived;
    // Whether the player won the game
    private final boolean won;

    /**
     * Constructs a new RankingEntry with the specified attributes.
     *
     * @param name The player's username.
     * @param score The player's score.
     * @param daysSurvived The number of days the player survived.
     * @param won Whether the player won the game.
     */
    public PlayerRankingEntry(String name, int score, int daysSurvived, boolean won) {
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

    public int getDaysSurvived() {
        return daysSurvived;
    }

    public boolean isWon() {
        return won;
    }
}
