package usecases.signup;

/**
 * Data class for encapsulating output data from the signup use case.
 * This class contains the result of the signup operation, including a success indicator
 * and a message detailing the outcome of the operation.
 */
public class SignupOutputData {
    private final boolean signupSuccess;
    private final String message;

    /**
     * Constructs a new SignupOutputData object with the specified success indicator and message.
     *
     * @param signupSuccess A boolean indicating whether the signup was successful.
     * @param message A string message describing the result of the signup operation.
     *                This can be a success message or an error message.
     */
    public SignupOutputData(boolean signupSuccess, String message) {
        this.signupSuccess = signupSuccess;
        this.message = message;
    }

    /**
     * Indicates whether the signup was successful.
     *
     * @return True if the signup was successful, otherwise false.
     */
    public boolean isSignupSuccess() {
        return signupSuccess;
    }

    /**
     * Retrieves the message associated with the signup operation.
     *
     * @return A string message representing the result of the signup operation.
     */
    public String getMessage() {
        return message;
    }
}
