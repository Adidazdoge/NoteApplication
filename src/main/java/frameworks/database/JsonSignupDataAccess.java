package frameworks.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Player;
import usecases.accountsignup.SignupDataAccessInterface;

/**
 * A JSON-based implementation of the PlayerRepository interface.
 */
public class JsonSignupDataAccess implements SignupDataAccessInterface {
    private final FileDatabase<Player> database;
    private List<Player> players;

    /**
     * Constructs a JsonSignupDataAccess instance and loads the players from the specified file.
     *
     * @param filePath The file path for the player database.
     * @throws IOException If an I/O error occurs while loading the file.
     */
    public JsonSignupDataAccess(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<Player>>() {});
        // Ensure players is mutable
        this.players = new ArrayList<>(database.load());
        System.out.println("Loaded players: " + players);
    }

    /**
     * Finds a player by their username.
     *
     * @param username The username to search for.
     * @return The Player object if found, or null if no player with the given username exists.
     */
    public Player findByUsername(String username) {
        return players.stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        final Player found = findByUsername(username);
        return found != null;
    }

    /**
     * Generates a unique player ID based on the provided username and password.
     *
     * This method combines the username and password into a single string and applies
     * the SHA-256 hashing algorithm to generate a unique, fixed-length player ID.
     *
     * @param username The player's username.
     * @param password The player's password.
     * @return A unique player ID as a hexadecimal string.
     * @throws RuntimeException If the SHA-256 hashing algorithm is not available.
     */
    public static String generatePlayerID(String username, String password) {
        try {
            // Combine username and password
            final String input = username + ":" + password;

            // Create SHA-256 hash
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hexadecimal string
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                final String hex = Integer.toHexString(255 & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            // Return hashed ID
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating player ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void addUser(String username, String password) {
        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException("The username is already taken.");
        }

        players.add(new Player(generatePlayerID(username, password), username, password));

        try {
            database.save(players);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
