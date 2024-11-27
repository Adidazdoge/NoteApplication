package usecases.signup;

/**
 * Data class for encapsulating input data for the signup use case.
 * This class contains the information provided by the user for registration,
 * such as their desired username and password.
 */
public class SignupInputData {
    private final String username;
    private final String password;

    /**
     * Constructs a new SignupInputData object with the specified username and password.
     *
     * @param username The desired username for the new account.
     * @param password The desired password for the new account.
     */
    public SignupInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the desired username for the new account.
     *
     * @return A string representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the desired password for the new account.
     *
     * @return A string representing the password.
     */
    public String getPassword() {
        return password;
    }
}
