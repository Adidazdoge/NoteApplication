package interface_adapters.nevagateloginview;

/**
 * Interface for handling the Login page navigation logic.
 */
public interface NevagateLoginInterface {
    /**
     * Handles the navigation result and updates the UI.
     *
     * @param message A message indicating the result of the navigation.
     */
    void onNavigate(String message);
}
