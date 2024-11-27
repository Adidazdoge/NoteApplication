package usecases.login;

/**
 * Data class for encapsulating input data for the login use case.
 * Contains the username and password provided by the user.
 */
public class LoginInputData {
    private final String username;
    private final String password;

    /**
     * Constructs a new LoginInputData object with the specified username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username provided by the user.
     *
     * @return The username as a string.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password provided by the user.
     *
     * @return The password as a string.
     */
    public String getPassword() {
        return password;
    }
}
