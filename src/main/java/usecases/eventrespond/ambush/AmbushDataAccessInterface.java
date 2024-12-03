package usecases.eventrespond.ambush;

import entities.Event;
import entities.Inventory;

import java.util.Map;

/**
 * Interface for data access in the Ambush event.
 */
public interface AmbushDataAccessInterface {
    Event getEvent();
    void removeEvent();
    Inventory getInventory();

    /**
     * Returns player attributes as a map.
     * Key: Attribute name (e.g., "Social").
     * Value: Attribute value.
     * @return Map of player attributes.
     */
    Map<String, Integer> getPlayerAttributesAsMap();

    void changeFood(int amount);
    void changeWater(int amount);
    void changeWeapon(int amount);
    void changePeople(int amount);
}