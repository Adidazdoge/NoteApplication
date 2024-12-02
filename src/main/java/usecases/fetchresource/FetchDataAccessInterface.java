package usecases.fetchresource;

import entities.Inventory;
import entities.PlayerInfo;

/**
 * Data access of fetch, get player inventory, as well as num of day and action points left.
 */
public interface FetchDataAccessInterface {

    /**
     * Get player's inventory.
     * @return inventory
     */
    Inventory getInventory();

    /**
     * Get player info, we need action point, and days.
     * @return player info.
     */
    PlayerInfo getPlayerInfo();
}
