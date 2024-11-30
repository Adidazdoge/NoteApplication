package usecases.eventrespond.flood;

import entities.EntityConstants;
import entities.EventFlood;
import entities.Inventory;
import kotlin.Pair;
import usecases.eventrespond.RespondOutputData;

/**
 * Handles responses to the Flood event.
 */
public class FloodInteractor {

    public Pair<RespondOutputData, Boolean> evaluate(int choice, Inventory inventory, EventFlood event) {
        String message = "";
        int foodChange = 0, waterChange = 0;
        boolean success = true;

        if (choice == EntityConstants.FIRSTCHOICE) { // Fight (Evacuate)
            foodChange = EntityConstants.FLOODRESOURCELOSS;
            message = event.getEvacuateOutcome();
        } else if (choice == EntityConstants.SECONDCHOICE) { // Negotiate (Secure supplies)
            foodChange = EntityConstants.FLOODSECURELOSS;
            message = event.getSecureSuppliesOutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) { // Flee (Do nothing)
            foodChange = EntityConstants.FLOODDOINGNOTHINGLOSS;
            message = event.getDoNothingOutcome();
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, 0, 0, ""), success);
    }
}
