package usecases.gamelosedetecter;

import entities.Inventory;

/**
 * Interface for lose check.
 */
public class LoseInteractor implements LoseInputBoundary {
    private LoseDataAccessInterface dataaccess;
    private LoseOutputBoundary outputBoundary;

    public LoseInteractor(LoseDataAccessInterface dataaccess, LoseOutputBoundary outputBoundary) {
        this.dataaccess = dataaccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(LoseInputData inputData) {
        String loseMessage = "";
        final Inventory inventory = dataaccess.getInventory();
        final int food = inventory.getFood();
        final int water = inventory.getWater();
        final int weapon = inventory.getWeapon();
        final int people = inventory.getPeople();

        if (food <= 0) {
            loseMessage = "Without food, morale crumbled. The group dispersed, unable to survive together any longer.";
        }
        else if (water <= 0) {
            loseMessage = "With no water to sustain them, the group suffered dehydration. One by one, they perished.";
        }
        else if (people <= 0) {
            loseMessage = "Everyone in your group has perished. There is no one left to continue the journey.";
        }
        else if (weapon <= 0) {
            loseMessage = "Defenseless and exposed, your group was overtaken by a hostile faction. "
                    + "They took everything by force.";
        }

        if (!loseMessage.isEmpty()) {
            // Call output boundary with the failure message
            final int score = dataaccess.getPlayerInfo().getScore();
            final LoseOutputData outputData = new LoseOutputData(loseMessage, score);
            outputBoundary.preapareGameoverEarly(outputData);
        }
        // do nothing if player is not losing.
    }
}
