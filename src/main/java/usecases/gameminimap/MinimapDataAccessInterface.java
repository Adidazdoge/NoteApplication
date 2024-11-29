package usecases.gameminimap;

import entities.Maps;
import entities.PlayerLocation;

/**
 * To return the minimap (default) 9x9 , we need player's location, and whole map.
 */
public interface MinimapDataAccessInterface {

    /**
     * Get the current player location in player location data type(x and y all stored).
     * @return Player's location.
     */
    PlayerLocation getPlayerLocation();

    /**
     * Get the maps of the this game.
     * @return Map of the current game.
     */
    Maps getMaps();
}
