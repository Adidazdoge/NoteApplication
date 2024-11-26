package usecases.login;

import entities.Player;
import interface_adapters.gateways.PlayerRepository;

/**
 * Handles the logic for logging in a player.
 * Validates credentials against the existing database.
 */
public class LoginUseCase {
    private final PlayerRepository repository;

    /**
     * Constructs a LoginUseCase with the specified player repository.
     *
     * @param repository The repository to access player data.
     */
    public LoginUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the login logic for a player.
     *
     * @param username The username provided by the player.
     * @param password The password provided by the player.
     * @return A success message if login is successful, otherwise an error message.
     */
    public String execute(String username, String password) {
        final Player player = repository.findByUsername(username);
        if (player == null) {
            return "Login failed: Username not found.";
        }
        if (!player.validatePassword(password)) {
            return "Login failed: Incorrect password.";
        }
        return "Login successful! Welcome back, " + username + ".";
    }
}
