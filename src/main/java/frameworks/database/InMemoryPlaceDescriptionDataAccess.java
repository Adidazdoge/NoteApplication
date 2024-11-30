package frameworks.database;

import entities.Location;
import usecases.gameplacedescription.PlaceDescriptionDataAccessInterface;

/**
 * In-Memory implementation of PlaceDescriptionDataAccessInterface.
 */
public class InMemoryPlaceDescriptionDataAccess implements PlaceDescriptionDataAccessInterface {
    private final Location currentLocation;

    public InMemoryPlaceDescriptionDataAccess(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }
}
