package usecases.accountlogin;

/**
 * Interface for accessing user data related to login.
 * Defines methods for validating credentials and checking user existence.
 */
public interface LoginDataAccessInterface {
    /**
     * Validates the credentials of a user.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return True if the credentials are valid, otherwise false.
     */
    boolean validateCredentials(String username, String password);

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username The username to check.
     * @return True if the user exists, otherwise false.
     */
    boolean doesUserExist(String username);
}
