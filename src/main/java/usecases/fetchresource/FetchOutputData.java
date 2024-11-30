package usecases.fetchresource;

/**
 * Output datatype for fetch, which fethes all the thing in inventory.
 */
public class FetchOutputData {
    private int food;
    private int water;
    private int people;
    private int weapon;

    public FetchOutputData(int food, int water, int people, int weapon) {
        this.food = food;
        this.water = water;
        this.people = people;
        this.weapon = weapon;
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
