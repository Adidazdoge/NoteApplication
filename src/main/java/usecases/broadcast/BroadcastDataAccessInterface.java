package usecases.broadcast;

import entities.Inventory;
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
     * @param resourceChange The change in resources.
     */
    void updateInventory(int resourceChange);
}
