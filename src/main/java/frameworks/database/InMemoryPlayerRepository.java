package frameworks.database;

import java.util.HashMap;
import java.util.Map;

import entities.Player;
import interface_adapters.gateways.PlayerRepository;

/**
 * An in-memory implementation of the PlayerRepository interface.
 *
 * This class is used to store and retrieve Player objects in memory, primarily for testing
 * and development purposes. It uses a HashMap to store Player data, where the key is the
 * player's username and the value is the Player object.
 */
public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<String, Player> players = new HashMap<>();

    /**
     * Constructs an InMemoryPlayerRepository with a predefined set of players.
     *
     * This constructor initializes the repository with some sample players for testing
     * and demonstration purposes. Each player has a unique ID, username, and password.
     */
    public InMemoryPlayerRepository() {
        // todo
        //  Logic for storing the players information to the players MAP.
        //  players.put("user1", new Player("1", "user1", "password1"));
        //  players.put("user2", new Player("2", "user2", "password2"));
    }

    /**
     * Finds a Player by their username.
     *
     * This method retrieves the Player object from the in-memory store using the specified username.
     * If no player with the given username exists, it returns null.
     *
     * @param username The username of the player to find.
     * @return The Player object if found, or null if no player with the given username exists.
     */
    @Override
    public Player findByUsername(String username) {
        return players.get(username);
    }
}
