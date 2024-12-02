package usecases.dailybroadcast;

/**
 * Output data for the broadcast use case.
 */
public class BroadcastOutputData {
    private final String resultMessage;

    public BroadcastOutputData(String resultMessage) {
        this.resultMessage = resultMessage;

    }

    public String getResultMessage() {
        return resultMessage;
    }
}
