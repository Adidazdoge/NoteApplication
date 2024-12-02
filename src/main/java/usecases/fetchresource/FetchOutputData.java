package usecases.fetchresource;

/**
 * Output datatype for fetch, which fethes all the thing in inventory.
 */
public class FetchOutputData {
    private int food;
    private int water;
    private int people;
    private int weapon;
    private int day;
    private int actionpoint;

    public FetchOutputData(int food, int water, int people, int weapon, int day, int actionpoint) {
        this.food = food;
        this.water = water;
        this.people = people;
        this.weapon = weapon;
        this.day = day;
        this.actionpoint = actionpoint;
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

    public int getDay() {
        return day;
    }

    public int getActionpoint() {
        return actionpoint;
    }
}
