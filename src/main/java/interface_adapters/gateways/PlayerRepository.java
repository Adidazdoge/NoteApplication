package interface_adapters.gateways;

import entities.Player;

/**
 * Represents the interface for accessing and managing player data.
 */
public interface PlayerRepository {

    /**
     * Finds and retrieves a player by their username.
     *
     * This method searches the data source for a player with the specified username.
     * If a matching player is found, it returns the corresponding Player object.
     * Otherwise, it returns null.
     *
     * @param username The username of the player to find.
     * @return The Player object if the username exists in the data source, otherwise null.
     */
    Player findByUsername(String username);

    /**
     * Checks if the given username already exists in the data source.
     *
     * This method determines whether a player with the specified username is already
     * stored in the data source. It helps ensure username uniqueness during signup.
     *
     * @param username The username to check.
     * @return True if the username exists in the data source, otherwise false.
     */
    boolean isUsernameDuplicate(String username);

    /**
     * Adds a new player to the data source.
     *
     * This method saves the given Player object into the data source. It assumes
     * that the username is unique and does not perform additional duplicate checks.
     *
     * @param player The Player object to add to the data source.
     */
    void addPlayer(Player player);
}
