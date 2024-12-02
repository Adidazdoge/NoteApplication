package usecases.eventrespond.ambush;

import usecases.eventrespond.ambush.AmbushOutputData;

/**
 * Output boundary for handling Ambush event results.
 */
public interface AmbushOutputBoundary {
    void prepareSuccessView(AmbushOutputData outputData);
    void prepareFailureView(String errorMessage);
}
