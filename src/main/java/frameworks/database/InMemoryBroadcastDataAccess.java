package frameworks.database;

import entities.Inventory;
import entities.PlayerAttributes;
import usecases.dailybroadcast.BroadcastDataAccessInterface;

/**
 * An in-memory implementation of BroadcastDataAccessInterface for testing and development.
 */
public class InMemoryBroadcastDataAccess implements BroadcastDataAccessInterface {
    private PlayerAttributes attributes;
    private Inventory inventory;

    public InMemoryBroadcastDataAccess(PlayerAttributes attributes, Inventory inventory) {
        this.attributes = attributes;
        this.inventory = inventory;
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return attributes;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void updateInventory(int resourceChange) {
        inventory.changeWater(resourceChange);
    }
}
