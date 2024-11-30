package frameworks.database;

import entities.Inventory;
import entities.Location;
import usecases.dailygather.GatherDataAccessInterface;

/**
 * In-memory implementation of GatherDataAccessInterface.
 */
public class InMemoryGatherDataAccess implements GatherDataAccessInterface {
    // Player's inventory
    private final Inventory inventory;
    // Player's current location
    private Location location;

    public InMemoryGatherDataAccess(Inventory inventory, Location location) {
        this.inventory = inventory;
        this.location = location;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void decreaseResourceavailable() {
        location.decreaseresourceavailable();
    }

    @Override
    public void changefood(int foodgathered) {
        inventory.changeFood(foodgathered);
    }

    @Override
    public void changewater(int watergathered) {
        inventory.changeWater(watergathered);
    }

    @Override
    public void changeweapon(int weapongathered) {
        inventory.changeweapon(weapongathered);
    }
}
