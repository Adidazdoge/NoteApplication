package usecases.login;

import entities.Player;
import interface_adapters.gateways.PlayerRepository;

/**
 * Handles the login logic.
 * Validates the user's credentials and returns a response indicating success or failure.
 */
public class LoginUseCase {
    private final PlayerRepository repository;

    /**
     * Constructs a new LoginUseCase with the given player repository.
     *
     * @param repository The repository to access player data.
     */
    public LoginUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the login use case with the given login request.
     * @param request The login request containing the username and password.
     * @return A LoginResponse indicating the result of the login attempt.
     */
    public LoginResponse execute(LoginRequest request) {
        final Player player = repository.findByUsername(request.getUsername());
        if (player != null && player.validatePassword(request.getPassword())) {
            return new LoginResponse(true, "Login successful!", player.getId());
        }
        return new LoginResponse(false, "Invalid username or password.", null);
    }
}
