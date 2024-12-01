package usecases.eventrespond.shared;

import entities.Event;
import entities.Inventory;
import entities.PlayerAttributes;
import entities.PlayerLocation;

/**
 * DAO of respond, provide internal data as needed for interactor.
 */
public interface RespondDataAccessInterface {

    /**
     * The event which we are working on to respond, assuming already happened.
     * @return Event, contains description, choices, and such.
     */
    Event getEvent();

    /**
     * Return the player attributes as alot of event outcome are determine by competence and attribute of player.
     * ex: low social attribute would cause more likely of failure for negotiate.
     * @return return player attributes.
     */
    PlayerAttributes getPlayerAttributes();

    /**
     * Return the player's inventory as alot of event outcome are determine by what player have currently.
     * ex: low firepower(involves people and weapon) FightBack etc choices are likely to fail.
     * @return return player inventory.
     */
    Inventory getInventory();

    /**
     * Return the player location, for response like run away flee etc, we'll have to change it, thus we need it.
     * @return player's current location.
     */
    PlayerLocation getPlayerLocation();

    /**
     * How the amount of people changes after the event, for that choice player provide. call the change method in
     * inventory.
     * @param peoplechange change of people quantity.
     */
    void changePeople(int peoplechange);

    /**
     * How the amount of food changes after the event, for that choice player provide. call change method in inventory.
     * @param foodchange change of people quantity.
     */
    void changeFood(int foodchange);

    /**
     * How the amount of people changes after the event, for that choice player provide. call change method in inventory
     * @param waterchange change of people quantity.
     */
    void changeWater(int waterchange);

    /**
     * How the amount of weapon changes after the event, for that choice player provide. call change method in inventory
     * @param weaponchange change of people quantity.
     */
    void changeWeapon(int weaponchange);
}
