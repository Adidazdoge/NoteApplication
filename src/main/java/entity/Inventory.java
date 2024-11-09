package entity;

/**
 * Class reponsible for representing inventory and inventory only, provide initial resources, getter and change method.
 */
public class Inventory {

    private int food;
    private int water;
    private int firearm;
    private int nonfirearm;
    private int people;

    public Inventory() {
        this.food = Entityconstants.STARTERFOOD;
        this.water = Entityconstants.STARTERWATER;
        this.firearm = Entityconstants.STARTFIREARM;
        this.nonfirearm = Entityconstants.STARTERNONFIREARM;
        this.people = Entityconstants.STARTERPEOPLE;
    }

    public int getFood() {
        return food;
    }

    /**
     * Changing food in inventory, can be pos or neg.
     * @param foodchange the change which is happening.
     */
    public void changeFood(int foodchange) {
        this.food = this.food + foodchange;
    }

    public int getWater() {
        return water;
    }

    /**
     * Changing water in inventory, can be pos or neg.
     * @param Waterchange the change which is happening.
     */
    public void changeWater(int Waterchange) {
        this.water = this.water + Waterchange;
    }

    public int getFirearm() {
        return firearm;
    }

    /**
     * Changing firearm in inventory, can be pos or neg.
     * @param firearmChange the change which is happening.
     */
    public void changeFirearm(int firearmChange) {
        this.firearm = this.firearm + firearmChange;
    }

    public int getNonfirearm() {
        return nonfirearm;
    }

    /**
     * Changing nonfirearm in inventory, can be pos or neg.
     * @param nonfirearmChange the change which is happening.
     */
    public void changeNonfirearm(int nonfirearmChange) {
        this.nonfirearm = this.nonfirearm + nonfirearmChange;
    }

    public int getPeople() {
        return people;
    }

    /**
     * Changing people in inventory, can be pos or neg.
     * @param peopleChange the change which is happening.
     */
    public void changePeople(int peopleChange) {
        this.people = this.people + peopleChange;
    }

}
