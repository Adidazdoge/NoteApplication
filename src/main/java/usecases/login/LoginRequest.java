package usecases.login;

/**
 * Represents the request object for the login use case.
 * Contains the username and password provided by the user.
 */
public class LoginRequest {
    private final String username;
    private final String password;

    /**
     * Constructs a new LoginRequest with the given username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the login request.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the login request.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
