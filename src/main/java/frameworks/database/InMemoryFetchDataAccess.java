package frameworks.database;

import entities.Inventory;
import usecases.fetchresource.FetchDataAccessInterface;

/**
 * In-Memory implementation of FetchDataAccessInterface.
 */
public class InMemoryFetchDataAccess implements FetchDataAccessInterface {
    private final Inventory inventory;

    /**
     * Constructor to initialize with an existing inventory.
     *
     * @param inventory Player's inventory object.
     */
    public InMemoryFetchDataAccess(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
