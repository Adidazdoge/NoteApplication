package interface_adapters.startallowcatepoint;

/**
 * Interface of allowcate point.
 */
public interface AllowcateInterface {

    /**
     * Update the ui after executed with the given info derived from outputdata.
     * @param point new point
     * @param social new social
     * @param luck new luck
     * @param mobilization new mobilization
     * @param thrift new thrift
     * @param generalship new generalship
     */
    void updateUiAllowcate(int point, int social, int luck, int mobilization, int thrift, int generalship);

    /**
     * Failure message if the usecase is failed. (insuffient skill point for example)
     * @param message fail message
     */
    void failureAllowcate(String message);
}
