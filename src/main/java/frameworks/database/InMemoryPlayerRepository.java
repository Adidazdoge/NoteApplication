package frameworks.database;

import java.util.HashMap;
import java.util.Map;

import entities.PlayerLogin;
import interface_adapters.gateways.PlayerRepository;

/**
 * An in-memory implementation of the PlayerRepository interface.
 *
 * This class is used to store and retrieve Player objects in memory, primarily for testing
 * and development purposes. It uses a HashMap to store Player data, where the key is the
 * player's username and the value is the Player object.
 */
public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<String, PlayerLogin> players = new HashMap<>();

    /**
     * Constructs an InMemoryPlayerRepository with a predefined set of players.
     *
     * This constructor initializes the repository with some sample players for testing
     * and demonstration purposes. Each player has a unique ID, username, and password.
     */
    public InMemoryPlayerRepository() {
        // todo
        //  Database import
        players.put("user1", new PlayerLogin("1", "user1", "password1"));
        players.put("user2", new PlayerLogin("2", "user2", "password2"));
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
    public PlayerLogin findByUsername(String username) {
        return players.get(username);
    }

    /**
     * Checks if the given username already exists in the repository.
     *
     * @param username The username to check.
     * @return True if the username exists, otherwise false.
     */
    @Override
    public boolean isUsernameDuplicate(String username) {
        return players.containsKey(username);
    }

    /**
     * Update the new player object in Hashmap players.
     *
     * @param playerLogin The object for Player.
     */
    @Override
    public void addPlayer(PlayerLogin playerLogin) {
        players.put(playerLogin.getUsername(), playerLogin);
    }
}
