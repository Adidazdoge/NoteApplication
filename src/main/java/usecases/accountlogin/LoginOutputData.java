package usecases.accountlogin;

/**
 * Data class for encapsulating output data from the login use case.
 * This class contains the result of the login operation, including a success indicator
 * and a message detailing the outcome of the operation.
 */
public class LoginOutputData {
    private final boolean loginSuccess;
    private final String message;

    /**
     * Constructs a new LoginOutputData object with the specified success indicator and message.
     *
     * @param loginSuccess A boolean indicating whether the login was successful.
     * @param message A string message describing the result of the login operation.
     *                This can be a success message or an error message.
     */
    public LoginOutputData(boolean loginSuccess, String message) {
        this.loginSuccess = loginSuccess;
        this.message = message;
    }

    /**
     * Indicates whether the login was successful.
     *
     * @return True if the login was successful, otherwise false.
     */
    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    /**
     * Retrieves the message associated with the login operation.
     *
     * @return A string message representing the result of the login operation.
     */
    public String getMessage() {
        return message;
    }
}
