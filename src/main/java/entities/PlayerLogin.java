package entities;

/**
 * Represents a player in the game.
 * This entity contains information about the player, such as their ID, username, and password.
 * The Player class encapsulates the player's identity and authentication logic.
 */
public class PlayerLogin {
    // Unique identifier for the player
    private final String id;
    // The player's username
    private final String username;
    // The player's password
    private final String password;

    /**
     * Constructs a new Player with the specified ID, username, and password.
     *
     * @param id        A unique identifier for the player.
     *                  This could be a database-generated ID.
     *                  It uniquely distinguishes this player from all others in the system.
     * @param username  The username chosen by the player for logging into the game.
     * @param password  The player's password for authentication.
     */
    public PlayerLogin(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the unique identifier for the player.
     *
     * @return The player's unique ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the username of the player.
     *
     * @return The player's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Validates the player's password by comparing it with the provided input.
     *
     * @param inputPassword The password input provided for authentication.
     * @return True if the input matches the player's password, otherwise false.
     */
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
