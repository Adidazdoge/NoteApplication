package usecases.eventrespond.blizzard;

import entities.EntityConstants;
import entities.EventBlizzard;
import usecases.eventrespond.shared.*;

/**
 * Interactor for handling player responses to a Blizzard event.
 * Implements the RespondInputBoundary interface.
 */
public class BlizzardEventInteractor implements RespondInputBoundary {
    private final RespondDataAccessInterface dataAccess;
    private final RespondOutputBoundary outputBoundary;

    public BlizzardEventInteractor(RespondDataAccessInterface dataAccess, RespondOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RespondInputData inputData) {
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
        RespondOutputData outputData = new RespondOutputData(message, foodChange, waterChange, 0, 0, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
