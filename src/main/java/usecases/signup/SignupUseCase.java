package usecases.signup;

import entities.Player;
import interface_adapters.gateways.PlayerRepository;

/**
 * Handles the logic for signing up a new player.
 * Ensures usernames are unique and adds new players to the database.
 */
public class SignupUseCase {
    private final PlayerRepository repository;

    /**
     * Constructs a SignupUseCase with the specified player repository.
     *
     * @param repository The repository to access player data.
     */
    public SignupUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the signup logic for a new player.
     *
     * @param username The desired username of the new player.
     * @param password The desired password of the new player.
     * @return A success message if signup is successful, otherwise an error message.
     */
    public String execute(String username, String password) {
        if (repository.isUsernameDuplicate(username)) {
            return "Signup failed: Username '" + username + "' is already taken.";
        }
        // Generate a new unique ID (for simplicity, use current size + 1)
        final String newId = String.valueOf(repository.hashCode() + 1);
        final Player newPlayer = new Player(newId, username, password);
        repository.addPlayer(newPlayer);
        return "Signup successful! Welcome, " + username + ".";
    }
}
