package usecases.endprocesshorde;

import entities.Horde;
import entities.Inventory;
import entities.PlayerAttributes;
import entities.PlayerInfo;

/**
 * Horde data access interface. Require the horde dataclass, player's location. Assume this is day 60, jumping to 61,
 * this usecase will generate a description of outcome, jump to gameover page, displaying score.
 */
public interface HordeDataAccessInterface {
    /**
     * Get inventory of player.
     * @return inventory of player.
     */
    Inventory getInventory();

    /**
     * Get horde info.
     * @return get horde info.
     */
    Horde getHorde();

    /**
     * Get player attribute we need generalship.
     * @return attribute of player.
     */
    PlayerAttributes getPlayerAttributes();

    /**
     * Set player won states to true.
     * @param won winning status updated.
     */
    void setWon(boolean won);

    /**
     * Get player info because we need to show the score.
     * @return Player info data type.
     */
    PlayerInfo getPlayerInfo();
}
