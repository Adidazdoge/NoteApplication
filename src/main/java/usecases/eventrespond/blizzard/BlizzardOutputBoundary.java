package usecases.eventrespond.blizzard;

import usecases.eventrespond.blizzard.BlizzardOutputData;

/**
 * Output boundary for handling Blizzard event results.
 */
public interface BlizzardOutputBoundary {
    void prepareSuccessView(BlizzardOutputData outputData);
    void prepareFailureView(String errorMessage);
}
