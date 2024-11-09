package entity;

/**
 * Player class of the program, could be used for scoreborad extension of the program, ranked by score & won.
 * This classes should only be responsible for keeping and updating the data, not score calculation process.
 * The class should only be updating any instance (player of it) once and which is after game ends, not termination!
 * The getter might be called multiple time for score board representation.
 */
public class Player {
    private String name;
    private int score;
    private int dayssurvived;
    private boolean won;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.dayssurvived = 0;
        this.won = false;
    }

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

    public int getDayssurvived() {
        return dayssurvived;
    }

    public void setDayssurvived(int dayssurvived) {
        this.dayssurvived = dayssurvived;
    }

    public boolean getWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

}
