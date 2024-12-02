package usecases.startallowcate;

/**
 * Output boundary of allowcate, preparesuccessview here, newly updated attributes, or failed view with failed message.
 */
public interface AllowcateOutputBoundary {
    /**
     * If success, then use new updated attributes stored in outputdata to create new view for player.
     * @param outputData all new updated attribute point.
     */
    void NevagateStartGame(AllowcateOutputData outputData);

    /**
     * If failure, such as points is already zero, show player this is invaild move, or don't let them click at all.
     * @param failmessage fail message.
     */
    void preparefailureview(String failmessage);
}
