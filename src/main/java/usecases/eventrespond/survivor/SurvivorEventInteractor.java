package usecases.eventrespond.survivor;

import entities.EntityConstants;
import entities.EventSurvivorJoins;
import usecases.eventrespond.survivor.SurvivorInputData;
import usecases.eventrespond.survivor.SurvivorOutputBoundary;
import usecases.eventrespond.survivor.SurvivorDataAccessInterface;

/**
 * Interactor for handling player responses to a Survivor Encounter event.
 * Implements the SurvivorInputBoundary interface.
 */
public class SurvivorEventInteractor implements SurvivorInputBoundary {
    private final SurvivorDataAccessInterface dataAccess;
    private final SurvivorOutputBoundary outputBoundary;

    public SurvivorEventInteractor(SurvivorDataAccessInterface dataAccess, SurvivorOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SurvivorInputData inputData) {
        EventSurvivorJoins survivorEvent = (EventSurvivorJoins) dataAccess.getEvent();
        int choice = inputData.getChoice();

        int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;
        String message;

        switch (choice) {
            case EntityConstants.FIRSTCHOICE: // Accept survivors
                peopleChange = EntityConstants.SURVIVORACCEPTPEOPLEGAIN;
                message = survivorEvent.getAcceptoutcome();
                break;
            case EntityConstants.SECONDCHOICE: // Reject survivors
                message = survivorEvent.getRejectoutcome();
                break;
            case EntityConstants.THIRDCHOICE: // Attempt to rob survivors
                if (dataAccess.getInventory().getfirepower() >= EntityConstants.SURVIVORROBBERYPOWER) {
                    foodChange = EntityConstants.SURVIVORROBBERYGAINFOOD;
                    suppliesChange = EntityConstants.SURVIVORROBBERYGAINSUPPLIES;
                    message = survivorEvent.getRoboutcomesuccess();
                } else {
                    foodChange = EntityConstants.SURVIVORROBBERYFAILLOSSFOOD;
                    peopleChange = EntityConstants.SURVIVORROBBERYFAILLOSSPEOPLE;
                    message = survivorEvent.getRoboutcomefail();
                }
                break;
            default: // Invalid choice
                outputBoundary.prepareFailureView("Invalid choice provided.");
                return;
        }

        // Apply changes to inventory
        dataAccess.changeFood(foodChange);
        dataAccess.changeWater(waterChange);
        dataAccess.changeWeapon(suppliesChange);
        dataAccess.changePeople(peopleChange);
        dataAccess.removeEvent();

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
        SurvivorOutputData outputData = new SurvivorOutputData(message, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
