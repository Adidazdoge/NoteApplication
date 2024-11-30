package usecases.fetchresource;

/**
 * Output boundary for fetch.
 */
public interface FetchOutputBoundary {

    /**
     * Prepare success view given outputdata.
     * @param outputdata outputdata, which are like food, water, people, weapon.
     */
    void prepareSuccessview(FetchOutputData outputdata);
}
