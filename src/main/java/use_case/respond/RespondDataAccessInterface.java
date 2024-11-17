package use_case.respond;

import entity.Event;
import entity.Inventory;
import entity.PlayerAttributes;
import entity.PlayerLocation;

/**
 * DAO of respond, provide internal data as needed for interactor.
 */
public interface RespondDataAccessInterface {

    /**
     * The event which we are working on to respond, assuming already happened.
     * @return Event, contains description, choices, and such.
     */
    Event getevent();

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
    void changepeople(int peoplechange);

    /**
     * How the amount of food changes after the event, for that choice player provide. call change method in inventory.
     * @param foodchange change of people quantity.
     */
    void changefood(int foodchange);

    /**
     * How the amount of people changes after the event, for that choice player provide. call change method in inventory
     * @param waterchange change of people quantity.
     */
    void changewater(int waterchange);

    /**
     * How the amount of weapon changes after the event, for that choice player provide. call change method in inventory
     * @param weaponchange change of people quantity.
     */
    void changeweapon(int weaponchange);

    /**
     * If for an event's responds alter the location, call this method which calls player location setxcoordinate
     * method.
     * @param newx new x coordination of the player after the event.
     */
    void setplayerxcoor(int newx);

    /**
     * If for an event's responds alter the location, call this method which calls player location setycoordinate
     * method.
     * @param newy new y coordination of the player after the event.
     */
    void setplayerycoor(int newy);
}
