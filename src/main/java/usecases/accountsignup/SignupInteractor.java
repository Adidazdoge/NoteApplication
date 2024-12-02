package usecases.accountsignup;

/**
 * Use case interactor for signup.
 * Implements the core logic for processing user registration requests,
 * including username uniqueness checks and user creation.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupDataAccessInterface dataAccessInterface;
    private final SignupOutputBoundary outputBoundary;

    /**
     * Constructs a SignupInteractor with the specified data access interface and output boundary.
     *
     * @param dataAccessInterface The interface for accessing user data for signup.
     * @param outputBoundary The output boundary for presenting signup results.
     */
    public SignupInteractor(SignupDataAccessInterface dataAccessInterface, SignupOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the signup use case.
     * Checks if the username is unique, creates a new user, and prepares the appropriate view.
     *
     * @param inputData The input data containing the username and password for registration.
     */
    @Override
    public void execute(SignupInputData inputData) {
        final String username = inputData.getUsername();
        final String password = inputData.getPassword();

        if (dataAccessInterface.isUsernameTaken(username)) {
            outputBoundary.prepareFailureView(new SignupOutputData(
                    false,
                    "Signup failed. The username is already taken!"
            ));
            return;
        }

        dataAccessInterface.addUser(username, password);

        outputBoundary.prepareSuccessView(new SignupOutputData(
                true,
                "Signup successful!"
        ));
    }
}
