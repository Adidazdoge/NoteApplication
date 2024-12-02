package usecases.eventrespond.blizzard;

import entities.EntityConstants;
import entities.EventBlizzard;
import usecases.eventrespond.blizzard.BlizzardInputData;
import usecases.eventrespond.blizzard.BlizzardOutputBoundary;
import usecases.eventrespond.blizzard.BlizzardDataAccessInterface;

/**
 * Interactor for handling player responses to a Blizzard event.
 * Implements the BlizzardInputBoundary interface.
 */
public class BlizzardEventInteractor implements BlizzardInputBoundary {
    private final BlizzardDataAccessInterface dataAccess;
    private final BlizzardOutputBoundary outputBoundary;

    public BlizzardEventInteractor(BlizzardDataAccessInterface dataAccess, BlizzardOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(BlizzardInputData inputData) {
        EventBlizzard blizzardEvent = (EventBlizzard) dataAccess.getEvent();
        int choice = inputData.getChoice();

        int foodChange = 0, waterChange = 0;
        String message;

        if (choice == EntityConstants.FIRSTCHOICE) {
            // Secure shelter
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD / 2;
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER / 2;
            message = blizzardEvent.getFightOutcome();
        } else if (choice == EntityConstants.SECONDCHOICE) {
            // Prepare supplies
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD;
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER;
            message = blizzardEvent.getNegotiateOutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) {
            // Do nothing
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD * 2;
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER * 2;
            message = blizzardEvent.getFleeOutcome();
        } else {
            // Invalid choice
            outputBoundary.prepareFailureView("Invalid choice provided. Please select a valid option.");
            return;
        }

        // Apply resource changes to the inventory
        dataAccess.changeFood(foodChange);
        dataAccess.changeWater(waterChange);

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange + ".";
        BlizzardOutputData outputData = new BlizzardOutputData(message, foodChange, waterChange, 0, 0, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
