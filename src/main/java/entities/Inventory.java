package entities;

/**
 * Class reponsible for representing inventory and inventory only, provide initial resources, getter and change method.
 */
public class Inventory {

    private int food;
    private int water;
    private int weapon;
    private int people;

    public Inventory() {
        this.food = EntityConstants.STARTERFOOD;
        this.water = EntityConstants.STARTERWATER;
        this.weapon = EntityConstants.STARTWEAPON;
        this.people = EntityConstants.STARTERPEOPLE;
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

    /**
     * Calculatign the firepower of the group.
     * @return firepower total.
     */
    public double getfirepower() {
        // Calculate the number of people with weapon.
        final int pairs = Math.min(people, weapon);
        // Remaining people who are unarmed
        final int unarmedPeople = people - pairs;

        // Firepower: each pair contributes 5, and each unarmed person contributes 1
        return (pairs * EntityConstants.ARMEDPEOPLEPOWER)
                + unarmedPeople * EntityConstants.UNARMPEOPLEPOWER;
    }

    /**
     * Method of generating string description on the change of inventory.
     * @param foodChange change of food.
     * @param waterChange change of water.
     * @param peopleChange change of people.
     * @param weaponChange change of weapon.
     * @return String of the description.
     */
    public String generateResourceChangeMessage(final int foodChange, final int waterChange,
                                                final int weaponChange, final int peopleChange) {
        final StringBuilder message = new StringBuilder("Your group has ");
        boolean hasChanges = false;

        hasChanges |= appendChange(message, "food", foodChange);
        hasChanges |= appendChange(message, "water", waterChange);
        hasChanges |= appendChange(message, "people", peopleChange);
        hasChanges |= appendChange(message, "weapons", weaponChange);

        final String ans;
        if (!hasChanges) {
            ans = "Nothing has changed for your group.";
        }
        else {
            message.append(".");
            ans = message.toString();
        }

        return ans;
    }

    private boolean appendChange(final StringBuilder message, final String resourceName, final int change) {
        boolean ans = false;

        if (change != 0) {
            if (message.length() > EntityConstants.LENGTHMESSAGECOMMA) {
                message.append(", ");
            }
            if (change > 0) {
                message.append("gained ").append(change).append(" units of ").append(resourceName);
            }
            else {
                message.append("lost ").append(Math.abs(change)).append(" units of ").append(resourceName);
            }
            ans = true;
        }

        return ans;
    }

}
