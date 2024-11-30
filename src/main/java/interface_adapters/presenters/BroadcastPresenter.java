package interface_adapters.presenters;

import usecases.dailybroadcast.BroadcastOutputBoundary;
import usecases.dailybroadcast.BroadcastOutputData;

/**
 * The BroadcastPresenter class processes use case output for the broadcast
 * and converts it into a format suitable for the UI.
 */
public class BroadcastPresenter implements BroadcastOutputBoundary {
    /**
     * Prepares the success view for the broadcast.
     *
     * @param outputData The output data from the broadcast use case.
     */
    @Override
    public void prepareSuccessView(BroadcastOutputData outputData) {
        System.out.println("Broadcast Result: " + outputData.getResultMessage());
        System.out.println("Survivors Found: " + outputData.getSurvivorsFound());
        System.out.println("Resources Found: " + outputData.getResourcesFound());
        if (outputData.isAttractedZombies()) {
            System.out.println("Warning: Zombies were attracted!");
        }
    }

    /**
     * Prepares the failure view for the broadcast.
     *
     * @param errorMessage The error message indicating why the broadcast failed.
     */
    @Override
    public void prepareFailureView(String errorMessage) {
        System.out.println("Broadcast Failed: " + errorMessage);
    }
}
