package usecases.eventrespond.blizzard;

import entities.Event;
import entities.Inventory;
import entities.PlayerAttributes;

/**
 * Interface for data access in the Blizzard event.
 */
public interface BlizzardDataAccessInterface {
    Event getEvent();
    Inventory getInventory();
    PlayerAttributes getPlayerAttributes();

    void changeFood(int amount);
    void changeWater(int amount);
}
