package usecases.accountlogin;

/**
 * Use case interactor for login.
 * Implements the core logic for processing login requests,
 * including credential validation and user existence checks.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface dataAccess;
    private final LoginOutputBoundary outputBoundary;

    /**
     * Constructs a LoginInteractor with the specified data access interface and output boundary.
     *
     * @param dataAccess The interface for accessing user data for login.
     * @param outputBoundary The output boundary for presenting login results.
     */
    public LoginInteractor(LoginDataAccessInterface dataAccess, LoginOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the login use case.
     * Validates the user's credentials and prepares the appropriate view (success or failure).
     *
     * @param inputData The input data containing the username and password.
     */
    @Override
    public void execute(LoginInputData inputData) {
        final String username = inputData.getUsername();
        final String password = inputData.getPassword();

        if (!dataAccess.doesUserExist(username)) {
            outputBoundary.prepareFailureView(new LoginOutputData(false, "User does not exist."));
            return;
        }

        if (!dataAccess.validateCredentials(username, password)) {
            outputBoundary.prepareFailureView(new LoginOutputData(false, "Invalid credentials."));
            return;
        }

        outputBoundary.prepareSuccessView(new LoginOutputData(true, "Login successful!"));
    }
}
