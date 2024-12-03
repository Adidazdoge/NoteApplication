package entities;

public class PlayerRankingEntry {
    private String name;
    private int score;
    private int daysSurvived;
    private boolean won;

    // Default constructor
    public PlayerRankingEntry() {

    }

    // All-args constructor
    public PlayerRankingEntry(String name, int score, int daysSurvived, boolean won) {
        this.name = name;
        this.score = score;
        this.daysSurvived = daysSurvived;
        this.won = won;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
