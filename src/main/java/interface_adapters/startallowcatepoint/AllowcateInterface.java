package interface_adapters.startallowcatepoint;

/**
 * Interface of allowcate point.
 */
public interface AllowcateInterface {
    /**
     * Failure message if the usecase is failed. (insuffient skill point for example)
     * @param message fail message
     */
    void failureAllowcate(String message);
}
