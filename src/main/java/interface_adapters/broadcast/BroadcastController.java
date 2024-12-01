package interface_adapters.broadcast;

import usecases.dailybroadcast.BroadcastInputBoundary;
import usecases.dailybroadcast.BroadcastInputData;

/**
 * The BroadcastController class is responsible for handling user input for broadcasts.
 * It acts as a bridge between the UI and the broadcast use case.
 */
public class BroadcastController {
    private final BroadcastInputBoundary inputBoundary;

    /**
     * Constructs a BroadcastController with the given input boundary.
     *
     * @param inputBoundary The use case input boundary for broadcasts.
     */
    public BroadcastController(BroadcastInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles the broadcast action by invoking the use case with the provided type.
     *
     * @param broadcastType The type of broadcast action (e.g., "SOS", "Resources").
     */
    public void handleBroadcast(String broadcastType) {
        final BroadcastInputData inputData = new BroadcastInputData(broadcastType);
        inputBoundary.execute(inputData);
    }
}
