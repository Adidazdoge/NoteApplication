package usecases.broadcast;

/**
 * Input data for the broadcast use case.
 */
public class BroadcastInputData {
    private final String broadcastType;

    public BroadcastInputData(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    public String getBroadcastType() {
        return broadcastType;
    }
}
