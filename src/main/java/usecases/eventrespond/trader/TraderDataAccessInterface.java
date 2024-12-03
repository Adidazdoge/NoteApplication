package usecases.eventrespond.trader;

import entities.Event;
import entities.Inventory;

import java.util.Map;

/**
 * Interface for data access in the Trader Encounter event.
 */
public interface TraderDataAccessInterface {
    Event getEvent();
    void removeEvent();
    Inventory getInventory();

    /**
     * Returns player attributes as a map.
     * Key: Attribute name (e.g., "Social").
     * Value: Attribute value.
     * @return Map of player attributes.
     */
    Map<String, Integer> getPlayerAttributes();

    /**
     * Returns player attributes as an object.
     * This is used when specific attribute methods are needed.
     * @return PlayerAttributes object.
     */
    PlayerAttributes getPlayerAttributesAsObject();

    void changeFood(int amount);
    void changeWater(int amount);
    void changeWeapon(int amount);
    void changePeople(int amount);
}