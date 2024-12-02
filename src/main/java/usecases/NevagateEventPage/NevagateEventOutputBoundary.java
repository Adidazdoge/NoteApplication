package usecases.NevagateEventPage;

/**
 * Navigate or navigate failed.
 */
public interface NevagateEventOutputBoundary {
    void nevagateEventPage(NevagateEventOutputdata outputdata);

    void nevagateEventFailed(String failmessage);
}
