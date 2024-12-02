package usecases.eventrespond.trader;

import entities.EntityConstants;
import entities.EventTraderEncounter;
import usecases.eventrespond.trader.TraderInputData;
import usecases.eventrespond.trader.TraderOutputBoundary;
import usecases.eventrespond.trader.TraderDataAccessInterface;

/**
 * Interactor for handling player responses to a Trader Encounter event.
 * Implements the TraderInputBoundary interface.
 */
public class TraderEventInteractor implements TraderInputBoundary {
    private final TraderDataAccessInterface dataAccess;
    private final TraderOutputBoundary outputBoundary;

    public TraderEventInteractor(TraderDataAccessInterface dataAccess, TraderOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(TraderInputData inputData) {
        EventTraderEncounter traderEvent = (EventTraderEncounter) dataAccess.getEvent();
        int choice = inputData.getChoice();

        int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;
        String message;

        if (choice == EntityConstants.FIRSTCHOICE) {
            // Trade with the trader
            if (dataAccess.getPlayerAttributes().getSocial() >= EntityConstants.TRADERNEGOTIATE) {
                foodChange = EntityConstants.TRADERTRADEGAINFOOD;
                waterChange = EntityConstants.TRADERTRADEGAINWATER;
                message = traderEvent.getTradeOutcomeSuccess();
            } else {
                foodChange = EntityConstants.TRADERTRADEFAILLOSSFOOD;
                waterChange = EntityConstants.TRADERTRADEFAILLOSSWATER;
                message = traderEvent.getTradeOutcomeScam();
            }
        } else if (choice == EntityConstants.SECONDCHOICE) {
            // Ignore the trader
            message = traderEvent.getIgnoreOutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) {
            // Rob the trader
            if (dataAccess.getInventory().getfirepower() >= EntityConstants.TRADERROBBERYPOWER) {
                foodChange = EntityConstants.TRADERROBBERYGAINFOOD;
                suppliesChange = EntityConstants.TRADERROBBERYGAINSUPPLIES;
                message = traderEvent.getRobOutcomeSuccess();
            } else {
                foodChange = EntityConstants.TRADERROBBERYFAILLOSSFOOD;
                peopleChange = EntityConstants.TRADERROBBERYFAILLOSSPEOPLE;
                message = traderEvent.getRobOutcomeFail();
            }
        } else {
            // Invalid choice
            outputBoundary.prepareFailureView("Invalid choice provided.");
            return;
        }

        // Apply changes to the inventory
        dataAccess.changeFood(foodChange);
        dataAccess.changeWater(waterChange);
        dataAccess.changeWeapon(suppliesChange);
        dataAccess.changePeople(peopleChange);
        dataAccess.removeEvent();

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
        TraderOutputData outputData = new TraderOutputData(message, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
