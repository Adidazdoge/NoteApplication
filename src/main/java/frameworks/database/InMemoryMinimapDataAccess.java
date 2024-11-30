package frameworks.database;

import entities.Maps;
import entities.PlayerLocation;
import usecases.gameminimap.MinimapDataAccessInterface;

/**
 * In-Memory implementation of MinimapDataAccessInterface.
 */
public class InMemoryMinimapDataAccess implements MinimapDataAccessInterface {
    private final PlayerLocation playerLocation;
    private final Maps gameMap;

    public InMemoryMinimapDataAccess(PlayerLocation playerLocation, Maps gameMap) {
        this.playerLocation = playerLocation;
        this.gameMap = gameMap;
    }

    @Override
    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    @Override
    public Maps getMaps() {
        return gameMap;
    }
}
