package usecases.dailybroadcast;

import java.util.Random;

import entities.Inventory;
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
        PlayerAttributes attributes = dataAccessInterface.getPlayerAttributes();
        Inventory inventory = dataAccessInterface.getInventory();

        // Check if the player has enough resources for a broadcast
        int resourceCost = 5;
        if (inventory.getWater() < resourceCost) {
            outputBoundary.prepareFailureView("Not enough resources to broadcast.");
            return;
        }

        // Deduct the resources
        dataAccessInterface.updateInventory(-resourceCost);

        // Simulate outcomes based on attributes
        Random random = new Random();
        boolean success = random.nextDouble() < (0.5 + attributes.getSocial() * 0.05);
        boolean attractedZombies = random.nextDouble() < (0.2 - attributes.getLuck() * 0.03);

        int survivorsFound = 0;
        int resourcesFound = 0;

        String resultMessage = "Your broadcast did not produce any results.";

        if (success) {
            survivorsFound = random.nextInt(3) + 1;
            resourcesFound = random.nextInt(10) + 5;
            resultMessage = "Your broadcast was successful!";
        }

        if (attractedZombies) {
            resultMessage += " However, it attracted zombies!";
        }

        BroadcastOutputData outputData = new BroadcastOutputData(resultMessage, survivorsFound, resourcesFound, attractedZombies);
        outputBoundary.prepareSuccessView(outputData);
    }
}
