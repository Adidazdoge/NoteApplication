package interface_adapters.broadcast;

import usecases.dailybroadcast.BroadcastOutputBoundary;
import usecases.dailybroadcast.BroadcastOutputData;

/**
 * The BroadcastPresenter class processes use case output for the broadcast
 * and converts it into a format suitable for the UI.
 */
public class BroadcastPresenter implements BroadcastOutputBoundary {
    private final BroadcastInterface view;

    public BroadcastPresenter(BroadcastInterface view) {
        this.view = view;
    }

    /**
     * Prepares the success view for the broadcast.
     *
     * @param outputData The output data from the broadcast use case.
     */
    @Override
    public void prepareSuccessView(BroadcastOutputData outputData) {
        view.updateUiBroadcast(
                outputData.getResultMessage()
        );
    }

    /**
     * Prepares the failure view for the broadcast.
     *
     * @param errorMessage The error message indicating why the broadcast failed.
     */
    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureBroadcast(errorMessage);
    }
}
