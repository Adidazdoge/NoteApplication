package interface_adapters.login;

/**
 * The LoginInterface defines the methods required for presenting the result of a login attempt.
 */
public interface LoginInterface {

    /**
     * Displays the result of the login attempt.
     *
     * @param message A string containing the result message of the login attempt.
     *                This could indicate a successful login or describe the reason for a login failure.
     */
    void displayLoginResult(String message);
}
