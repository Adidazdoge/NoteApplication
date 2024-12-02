package usecases.eventrespond.flood;

import usecases.eventrespond.flood.FloodOutputData;

/**
 * Output boundary for handling Flood event results.
 */
public interface FloodOutputBoundary {
    void prepareSuccessView(FloodOutputData outputData);
    void prepareFailureView(String errorMessage);
}
