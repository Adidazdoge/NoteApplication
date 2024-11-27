package usecases.login;

/**
 * Input boundary for the login use case.
 * Defines the interface for initiating the login process.
 */
public interface LoginInputBoundary {
    /**
     * Processes a login request with the provided input data.
     *
     * @param inputData The data required for login, such as username and password.
     */
    void execute(LoginInputData inputData);
}
