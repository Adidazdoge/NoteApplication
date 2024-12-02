package usecases.eventrespond.survivor;

import usecases.eventrespond.survivor.SurvivorOutputData;

/**
 * Output boundary for handling Survivor event results.
 */
public interface SurvivorOutputBoundary {
    void prepareSuccessView(SurvivorOutputData outputData);
    void prepareFailureView(String errorMessage);
}
