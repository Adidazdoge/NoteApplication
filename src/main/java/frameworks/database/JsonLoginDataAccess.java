package frameworks.database;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Player;
import usecases.accountlogin.LoginDataAccessInterface;

/**
 * A JSON-based implementation of the LoginDataAccessInterface.
 * This class provides methods to validate user credentials and check user existence.
 */
public class JsonLoginDataAccess implements LoginDataAccessInterface {
    private final FileDatabase<Player> database;
    private List<Player> players;

    /**
     * Constructs a new JsonLoginDataAccess object with the specified file path.
     *
     * @param filePath The path to the JSON file containing player data.
     * @throws IOException If there is an issue reading the JSON file.
     */
    public JsonLoginDataAccess(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<Player>>() {});
        this.players = database.load();
    }

    /**
     * Validates the credentials of a user.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return True if the credentials are valid, otherwise false.
     */
    @Override
    public boolean validateCredentials(String username, String password) {
        return players.stream()
                .anyMatch(player -> player.getUsername().equals(username)
                        && player.validatePassword(password));
    }

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username The username to check.
     * @return True if the user exists, otherwise false.
     */
    @Override
    public boolean doesUserExist(String username) {
        return players.stream()
                .anyMatch(player -> player.getUsername().equals(username));
    }
}
