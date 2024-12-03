package usecases.gamelosedetecter;

import entities.Inventory;
import entities.PlayerInfo;

/**
 * Use player's inventory to decide if they lose, if any is 0.
 */
public interface LoseDataAccessInterface {
    Inventory getInventory();

    PlayerInfo getPlayerInfo();

}
