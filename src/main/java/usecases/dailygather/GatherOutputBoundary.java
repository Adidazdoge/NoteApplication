package usecases.dailygather;

/**
 * Output boundary for gather use case, responsible for providing the new view after the use case executed.
 */
public interface GatherOutputBoundary {

    /**
     * Prepare the view for a successful gather use case.
     * @param outputdata outputdata been sent.
     */
    void prepareSuccessView(GatherOutputData outputdata);

    /**
     * Prepare the new for a failed gather use case, though I'm not sure how will this be failed, maybe player clicked
     * on gather after used all their daily available action point.
     * @param errorMessage error message for such failure.
     */
    void prepareFailureView(String errorMessage);
}
