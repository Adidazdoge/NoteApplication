package view;

/**
 * Represents a ViewModel for the ranking list.
 * Used to pass ranking data from the application to the UI.
 */
public class RankingViewModel {
    private final String name;
    private final int score;
    private final int daysSurvived;
    private final boolean won;

    /**
     * Constructs a new RankingViewModel with the specified attributes.
     *
     * @param name The player's name.
     * @param score The player's score.
     * @param daysSurvived The number of days the player survived.
     * @param won Whether the player won.
     */
    public RankingViewModel(String name, int score, int daysSurvived, boolean won) {
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
