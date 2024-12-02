package usecases.gamelosedetecter;

/**
 * Display brief description, how and why you lose.
 */
public class LoseOutputData {
    private String losedescription;

    public LoseOutputData(String losedescription) {
        this.losedescription = losedescription;
    }

    public String getLosedescription() {
        return losedescription;
    }
}
