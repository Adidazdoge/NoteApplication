package interface_adapters.gateways;

import entities.Player;

/**
 * The PlayerRepository interface defines the contract for accessing and managing
 * player data in the underlying data source (e.g., database, in-memory store).
 *
 * This interface abstracts the storage details, enabling the use of different
 * data source implementations without affecting the core business logic.
 */
public interface PlayerRepository {

    /**
     * Finds and retrieves a player by their username.
     * This method searches for a player in the data source using the given username
     * and returns a Player object if a match is found. If no player is found, it may
     * return null or throw an appropriate exception, depending on the implementation.
     *
     * @param username The username of the player to find.
     * @return A Player object if the username exists in the data source, otherwise null.
     */
    Player findByUsername(String username);
}
