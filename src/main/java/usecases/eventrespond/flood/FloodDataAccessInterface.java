package usecases.eventrespond.flood;

import entities.Event;
import entities.Inventory;
import entities.PlayerAttributes;

/**
 * Interface for data access in the Flood event.
 */
public interface FloodDataAccessInterface {
    Event getEvent();
    void removeEvent();
    Inventory getInventory();
    PlayerAttributes getPlayerAttributes();

    void changeFood(int amount);
    void changeWater(int amount);
    void changePeople(int amount);
}
