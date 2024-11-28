package usecases.dailybroadcast;

/**
 * Output data for the broadcast use case.
 */
public class BroadcastOutputData {
    private final String resultMessage;
    private final int survivorsFound;
    private final int resourcesFound;
    private final boolean attractedZombies;

    public BroadcastOutputData(String resultMessage, int survivorsFound, int resourcesFound, boolean attractedZombies) {
        this.resultMessage = resultMessage;
        this.survivorsFound = survivorsFound;
        this.resourcesFound = resourcesFound;
        this.attractedZombies = attractedZombies;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public int getSurvivorsFound() {
        return survivorsFound;
    }

    public int getResourcesFound() {
        return resourcesFound;
    }

    public boolean isAttractedZombies() {
        return attractedZombies;
    }
}
