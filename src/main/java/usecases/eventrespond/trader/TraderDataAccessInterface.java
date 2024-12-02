package usecases.eventrespond.trader;

import entities.Event;
import entities.Inventory;
import entities.PlayerAttributes;

/**
 * Interface for data access in the Trader event.
 */
public interface TraderDataAccessInterface {
    Event getEvent();
    Inventory getInventory();
    PlayerAttributes getPlayerAttributes();

    void changeFood(int amount);
    void changeWater(int amount);
    void changeWeapon(int amount);
    void changePeople(int amount);
}
