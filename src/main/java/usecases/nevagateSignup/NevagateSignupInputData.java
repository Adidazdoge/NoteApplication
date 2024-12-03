package usecases.nevagateSignup;

/**
 * Input data class for the NavigateSignup use case.
 * Encapsulates the necessary information to trigger the navigation logic.
 */
public class NevagateSignupInputData {
    private final String context;

    /**
     * Constructs a NevagateSignupInputData instance.
     *
     * @param context A string representing the navigation context (e.g., "Navigate to Signup").
     */
    public NevagateSignupInputData(String context) {
        this.context = context;
    }

    /**
     * Gets the navigation context.
     *
     * @return A string representing the context.
     */
    public String getContext() {
        return context;
    }
}
