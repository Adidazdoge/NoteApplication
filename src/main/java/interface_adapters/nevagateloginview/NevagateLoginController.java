package interface_adapters.nevagateloginview;

import usecases.nevagateLogin.NevagateLoginInputBoundary;
import usecases.nevagateLogin.NevagateLoginInputData;

/**
 * Controller for navigating to the Login page.
 */
public class NevagateLoginController {
    private final NevagateLoginInputBoundary inputBoundary;

    public NevagateLoginController(NevagateLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Triggers navigation to the Login page.
     */
    public void navigateToLogin() {
        final NevagateLoginInputData inputData = new NevagateLoginInputData();
        inputBoundary.execute(inputData);
    }
}
