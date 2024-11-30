package usecases.gameplacedescription;

import entities.Location;

/**
 * Require location info like temperature, threat level etc for more specific and smart description.
 */
public interface PlaceDescriptionDataAccessInterface {

    /**
     * The location of the player's current position.
     * @return location.
     */
    Location getLocation();
}
