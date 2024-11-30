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

    public Pair<RespondOutputData, Boolean> evaluate(Inventory inventory, EventBlizzard event) {
        String message = event.getOutcome();
        int foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD;
        int waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER;

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, 0, 0, ""), true);
    }
}
