package usecases.signup;

/**
 * Interface for accessing user data related to signup.
 * Defines methods for checking username uniqueness and adding a new user.
 */
public interface SignupDataAccessInterface {
    /**
     * Checks if the specified username is already taken.
     *
     * @param username The username to check.
     * @return True if the username is taken, otherwise false.
     */
    boolean isUsernameTaken(String username);

    /**
     * Adds a new user with the specified username and password.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     */
    void addUser(String username, String password);
}
