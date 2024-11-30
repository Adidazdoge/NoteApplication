package usecases.eventrespond.trader;

import entities.EntityConstants;
import entities.EventTraderEncounter;
import entities.Inventory;
import entities.PlayerAttributes;
import kotlin.Pair;
import usecases.eventrespond.RespondOutputData;

/**
 * Handles responses to the Trader encounter event.
 */
public class TraderInteractor {

    public Pair<RespondOutputData, Boolean> evaluate(PlayerAttributes attributes, int choice, Inventory inventory, EventTraderEncounter event) {
        String message = "";
        int foodChange = 0, waterChange = 0, suppliesChange = 0;
        boolean success = true;

        if (choice == EntityConstants.FIRSTCHOICE) { // Fight (rob)
            if (inventory.getfirepower() >= EntityConstants.TRADERROBBERYPOWER) {
                foodChange = EntityConstants.TRADERROBBERYGAINFOOD;
                suppliesChange = EntityConstants.TRADERROBBERYGAINSUPPLIES;
                message = event.getRobOutcomeSuccess();
            } else {
                foodChange = EntityConstants.TRADERROBBERYFAILLOSSFOOD;
                message = event.getRobOutcomeFail();
            }
        } else if (choice == EntityConstants.SECONDCHOICE) { // Negotiate
            if (attributes.getSocial() >= EntityConstants.TRADERNEGOTIATE) {
                foodChange = EntityConstants.TRADERTRADEGAINFOOD;
                waterChange = EntityConstants.TRADERTRADEGAINWATER;
                message = event.getTradeOutcomeSuccess();
            } else {
                foodChange = EntityConstants.TRADERTRADEFAILLOSSFOOD;
                waterChange = EntityConstants.TRADERTRADEFAILLOSSWATER;
                message = event.getTradeOutcomeScam();
            }
        } else if (choice == EntityConstants.THIRDCHOICE) { // Flee
            message = "You ignored the trader and moved on.";
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);
        inventory.changeweapon(suppliesChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, suppliesChange, 0, ""), success);
    }
}
