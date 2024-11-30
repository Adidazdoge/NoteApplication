package usecases.dailymove;

import entities.Maps;
import entities.PlayerAttributes;
import entities.PlayerLocation;

/**
 * Data access interface for move use case, retrieve player's current location, and attribute.
 * attribute which we care is mobilization, which determine how fast player can move.
 */
public interface MoveDataAccessInterface {

    /**
     * Return the player attributes from data access.
     * @return player attribute entity.
     */
    PlayerAttributes getPlayerAttributes();

    /**
     * Return the player location from data access.
     * @return player location.
     */
    PlayerLocation getPlayerLocation();

    /**
     * Return the map grid from data access, use to determine if vaild move on the edge etc.
     * @return return map.
     */
    Maps getMaps();

    /**
     * Update the player location.
     * @param newx newx of player
     * @param newy newy of player
     */
    void updatePlayerLocation(int newx, int newy);
}
