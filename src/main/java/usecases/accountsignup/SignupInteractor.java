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

        // Validate username and password format
        if (!isValidUsername(username)) {
            outputBoundary.prepareFailureView(new SignupOutputData(false,
                    "Signup failed. Username must be 5-15 characters, containing only letters and digits."));
            return;
        }

        if (!isValidPassword(password)) {
            outputBoundary.prepareFailureView(new SignupOutputData(false,
                    "Signup failed. Password must be 8-20 characters, containing at least one letter and one digit."));
            return;
        }

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

    /**
     * Validates the username format.
     *
     * @param username The username to validate.
     *                 - Must be 5-15 characters long.
     *                 - Can only contain letters and digits (a-z, A-Z, 0-9).
     * @return `true` if the username is valid, otherwise `false`.
     */
    private boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9]{5,15}");
    }

    /**
     * Validates the password format.
     *
     * @param password The password to validate.
     *                 - Must be 8-20 characters long.
     *                 - Must contain at least one letter and one digit.
     * @return `true` if the password is valid, otherwise `false`.
     */
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$");
    }
}
