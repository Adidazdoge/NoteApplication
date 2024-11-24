package usecases.login;

/**
 * Represents the response object for the login use case.
 * Contains the result of the login attempt, including success status, a message, and the player ID.
 */
public class LoginResponse {
    private final boolean success;
    private final String message;
    private final String playerId;

    /**
     * Constructs a new LoginResponse with the given success status, message, and player ID.
     *
     * @param success Indicates whether the login was successful.
     * @param message A message describing the result of the login attempt.
     * @param playerId The unique ID of the player if login is successful.
     */
    public LoginResponse(boolean success, String message, String playerId) {
        this.success = success;
        this.message = message;
        this.playerId = playerId;
    }

    /**
     * Checks whether the login was successful.
     *
     * @return True if the login was successful, otherwise false.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the message describing the login result.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the player ID if the login is successful.
     *
     * @return The player ID, or null if login failed.
     */
    public String getPlayerId() {
        return playerId;
    }
}

