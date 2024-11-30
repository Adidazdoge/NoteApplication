package frameworks.database;

import entities.Horde;
import entities.Inventory;
import entities.PlayerAttributes;
import entities.PlayerInfo;
import usecases.endprocesshorde.HordeDataAccessInterface;

/**
 * In-Memory implementation of HordeDataAccessInterface.
 */
public class InMemoryHordeDataAccess implements HordeDataAccessInterface {
    private final Inventory inventory;
    private final Horde horde;
    private final PlayerAttributes playerAttributes;
    private final PlayerInfo playerInfo;
    private boolean won;

    public InMemoryHordeDataAccess(Inventory inventory, Horde horde,
                                   PlayerAttributes playerAttributes, PlayerInfo playerInfo) {
        this.inventory = inventory;
        this.horde = horde;
        this.playerAttributes = playerAttributes;
        this.playerInfo = playerInfo;
        this.won = false;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Horde getHorde() {
        return horde;
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    @Override
    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
