package usecases.dailybroadcast;

import entities.Inventory;
import entities.Location;
import entities.PlayerAttributes;

/**
 * Data access interface for the broadcast use case.
 */
public interface BroadcastDataAccessInterface {
    /**
     * Gets the player's attributes.
     *
     * @return The player's attributes.
     */
    PlayerAttributes getPlayerAttributes();

    /**
     * Gets the player's inventory.
     *
     * @return The player's inventory.
     */
    Inventory getInventory();

    /**
     * Updates the player's inventory.
     *
     * @param peopleChange The change in resources.
     */
    void changePeople(int peopleChange);

    /**
     * Get player's current location.
     * @return location.
     */
    Location getLocation();

    /**
     * Get player's action point left.
     * @return action point.
     */
    int getActionPoint();

    /**
     * Set player's action point.
     * @param actionPoint action point.
     */
    void setActionPoint(int actionPoint);
}
