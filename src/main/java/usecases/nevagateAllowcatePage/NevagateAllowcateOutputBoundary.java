package usecases.nevagateAllowcatePage;

/**
 * Output boundary, should be able to go to allowcate point page or, it not able restrict player.
 */
public interface NevagateAllowcateOutputBoundary {

    /**
     * If player is able to go.
     */
    void nevagateAllowcatePage();

    /**
     * If player is restricted.
     * @param errormessage error message.
     */
    void nevagateAllowcateFail(String errormessage);
}
