package usecases.gamenewday;

/**
 * Output data of newday after is done, should return a message saying what happened, how many people gained, lose, etc.
 * Or, was the usecase a success, would will for example if already 60 days and still called this use case to day 61.
 */
public class NewdayOutputData {
    private String message;
    private boolean success;
    private String failmessage;
    private int food;
    private int water;
    private int people;
    private int weapon;

    public NewdayOutputData(String message, boolean success, String failmessage,
                            int food, int water, int people, int weapon) {
        this.message = message;
        this.success = success;
        this.failmessage = failmessage;
        this.food = food;
        this.water = water;
        this.people = people;
        this.weapon = weapon;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailmessage() {
        return failmessage;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public int getPeople() {
        return people;
    }

    public int getWeapon() {
        return weapon;
    }
}
