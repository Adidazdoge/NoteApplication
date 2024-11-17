package entity;

/**
 * Class reponsible for representing inventory and inventory only, provide initial resources, getter and change method.
 */
public class Inventory {

    private int food;
    private int water;
    private int weapon;
    private int people;

    public Inventory() {
        this.food = Entityconstants.STARTERFOOD;
        this.water = Entityconstants.STARTERWATER;
        this.weapon = Entityconstants.STARTWEAPON;
        this.people = Entityconstants.STARTERPEOPLE;
    }

    public int getFood() {
        return food;
    }

    /**
     * Changing food in inventory, can be pos or neg.
     * @param foodChange the change which is happening.
     */
    public void changeFood(int foodChange) {
        this.food = this.food + foodChange;
    }

    public int getWater() {
        return water;
    }

    /**
     * Changing water in inventory, can be pos or neg.
     * @param waterChange the change which is happening.
     */
    public void changeWater(int waterChange) {
        this.water = this.water + waterChange;
    }

    public int getWeapon() {
        return weapon;
    }

    /**
     * Changing firearm in inventory, can be pos or neg.
     * @param weaponChange the change which is happening.
     */
    public void changeweapon(int weaponChange) {
        this.weapon = this.weapon + weaponChange;
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
