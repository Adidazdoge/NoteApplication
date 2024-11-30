package usecases.eventrespond.blizzard;

import entities.EntityConstants;
import entities.EventBlizzard;
import entities.Inventory;
import kotlin.Pair;
import usecases.eventrespond.RespondOutputData;

/**
 * Handles responses to the Blizzard event.
 */
public class BlizzardInteractor {

    public Pair<RespondOutputData, Boolean> evaluate(int choice, Inventory inventory, EventBlizzard event) {
        String message = "";
        int foodChange = 0, waterChange = 0;
        boolean success = true;

        if (choice == EntityConstants.FIRSTCHOICE) { // Fight (Secure shelter)
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD / 2; // Halve the resource loss
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER / 2;
            message = event.getFightOutcome();
        } else if (choice == EntityConstants.SECONDCHOICE) { // Negotiate (Prepare supplies)
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD; // Normal resource loss
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER;
            message = event.getNegotiateOutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) { // Flee (Do nothing)
            foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD * 2; // Double the resource loss
            waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER * 2;
            message = event.getFleeOutcome();
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, 0, 0, ""), success);
    }
}
