package usecases.dailybroadcast;

import entities.EntityConstants;
import entities.Location;
import entities.PlayerAttributes;

/**
 * Interactor for the broadcast use case.
 */
public class BroadcastInteractor implements usecases.dailybroadcast.BroadcastInputBoundary {
    private final usecases.dailybroadcast.BroadcastDataAccessInterface dataAccessInterface;
    private final usecases.dailybroadcast.BroadcastOutputBoundary outputBoundary;

    public BroadcastInteractor(usecases.dailybroadcast.BroadcastDataAccessInterface dataAccessInterface,
                               usecases.dailybroadcast.BroadcastOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(usecases.dailybroadcast.BroadcastInputData inputData) {
        final PlayerAttributes attributes = dataAccessInterface.getPlayerAttributes();
        final int actionpoint = dataAccessInterface.getActionPoint();
        final Location location = dataAccessInterface.getLocation();

        if (actionpoint > 0) {
            final int peoplegain = (int) Math.round(EntityConstants.BROADCASTGAIN
                    * (1 + attributes.getSocial() * EntityConstants.SOCIALIMPACTBROADCAST)
                    * location.getpeopleresourceavailable());
            final String successmessage = "Though Broadcast, " + peoplegain + " decided to join your group!";
            dataAccessInterface.setActionPoint(actionpoint - 1);
            dataAccessInterface.changePeople(peoplegain);
            final BroadcastOutputData outputData = new BroadcastOutputData(successmessage);
            outputBoundary.prepareSuccessView(outputData);
        }

    }
}
