package usecases.fetchresource;

import entities.Inventory;

/**
 * Data access of fetch, get player inventory.
 */
public interface FetchDataAccessInterface {

    /**
     * Get player's inventory.
     * @return inventory
     */
    Inventory getInventory();
}
