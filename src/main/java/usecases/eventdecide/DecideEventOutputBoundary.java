package usecases.eventdecide;

/**
 * Deicde event, output boundary, though I can't really think of a way for it to fail, for people who are writing this,
 * you can simply display "something went wrong" for fail view right now.
 */
public interface DecideEventOutputBoundary {
    /**
     * Outputs for a successful move.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(DecideEventOutputData outputData);

    /**
     * If the decide event is invaild, example: is on day 61? did player already lose?
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
