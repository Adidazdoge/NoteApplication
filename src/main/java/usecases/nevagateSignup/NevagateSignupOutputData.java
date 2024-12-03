package usecases.nevagateSignup;


/**
 * Output data class for the NavigateSignup use case.
 * Encapsulates the results of the navigation process.
 */
public class NevagateSignupOutputData {
    private final String message;

    /**
     * Constructs a NevagateSignupOutputData instance.
     *
     * @param message A message indicating the navigation result.
     */
    public NevagateSignupOutputData(String message) {
        this.message = message;
    }

    /**
     * Gets the navigation result message.
     *
     * @return A string containing the result message.
     */
    public String getMessage() {
        return message;
    }
}
