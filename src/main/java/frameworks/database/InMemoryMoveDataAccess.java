package frameworks.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import entities.*;
import entities.MapFactory;
import usecases.dailymove.MoveDataAccessInterface;

/**
 * In-Memory implementation of MoveDataAccessInterface.
 */
public class InMemoryMoveDataAccess implements MoveDataAccessInterface {
    private PlayerAttributes playerAttributes;
    private PlayerLocation playerLocation;
    private Maps maps;

    /**
     * Constructor to initialize with existing player data (e.g., loaded from a save).
     *
     * @param playerAttributes Existing player attributes.
     * @param playerLocation   Existing player location.
     * @param maps             Existing map data.
     */
    public InMemoryMoveDataAccess(PlayerAttributes playerAttributes, PlayerLocation playerLocation, Maps maps) {
        this.playerAttributes = playerAttributes;
        this.playerLocation = playerLocation;
        this.maps = maps;
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    @Override
    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    @Override
    public Maps getMaps() {
        return maps;
    }

    @Override
    public void updatePlayerLocation(int newx, int newy) {
        playerLocation.setXcoordinate(newx);
        playerLocation.setYcoordinate(newy);
    }
}
