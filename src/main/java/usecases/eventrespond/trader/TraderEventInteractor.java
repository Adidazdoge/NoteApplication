package usecases.eventrespond.trader;

import entities.EntityConstants;
import entities.EventTraderEncounter;
import usecases.eventrespond.shared.*;

/**
 * Interactor for handling player responses to a Trader Encounter event.
 * Implements the RespondInputBoundary interface.
 */
public class TraderEventInteractor implements RespondInputBoundary {
    private final RespondDataAccessInterface dataAccess;
    private final RespondOutputBoundary outputBoundary;

    public TraderEventInteractor(RespondDataAccessInterface dataAccess, RespondOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RespondInputData inputData) {
        EventTraderEncounter traderEvent = (EventTraderEncounter) dataAccess.getevent();
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
        dataAccess.changefood(foodChange);
        dataAccess.changewater(waterChange);
        dataAccess.changeweapon(suppliesChange);

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
        RespondOutputData outputData = new RespondOutputData(message, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
