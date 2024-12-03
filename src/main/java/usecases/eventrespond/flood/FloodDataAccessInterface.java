package usecases.eventrespond.flood;

import entities.Event;
import entities.Inventory;

import java.util.Map;

/**
 * Interface for data access in the Flood event.
 */
public interface FloodDataAccessInterface {
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

    void changeFood(int amount);
    void changeWater(int amount);
    void changePeople(int amount);
}