package interface_adapters.signup;

/**
 * The SignupInterface defines methods that the UI must implement
 * to display the results of a signup operation or handle errors.
 */
public interface SignupInterface {

    /**
     * Updates the UI to display the result of a successful signup.
     *
     * @param message The success message to display.
     */
    void displaySignupResult(String message);

    /**
     * Updates the UI to display an error message if the signup operation fails.
     *
     * @param errorMessage The error message to display.
     */
    void displaySignupError(String errorMessage);
}
