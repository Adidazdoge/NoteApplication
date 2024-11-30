package usecases.endprocesshorde;

import entities.EntityConstants;

/**
 * Interactor of horde processing, main logic happening.
 */
public class HordeInteractor implements HordeInputBoundary {
    private HordeDataAccessInterface dataaccess;
    private HordeOutputBoundary outputboundary;

    public HordeInteractor(HordeDataAccessInterface dataaccess, HordeOutputBoundary outputboundary) {
        this.dataaccess = dataaccess;
        this.outputboundary = outputboundary;
    }

    @Override
    public void execute(HordeInputData inputData) {
        String message = "";
        final int people = dataaccess.getInventory().getPeople();
        final int weapon = dataaccess.getInventory().getWeapon();
        final int generalship = dataaccess.getPlayerAttributes().getGeneralship();
        final int armed = Math.min(weapon, people);
        final double generalshipBonus = 1 + (generalship * 0.05);
        final double firepower = armed * EntityConstants.ARMEDPEOPLEPOWER * generalshipBonus;
        final double resourcerequired = dataaccess.getHorde().getDuration() * dataaccess.getInventory().getPeople()
                * (1 - EntityConstants.THRIFTIMPACTRESOURCELOSS * dataaccess.getPlayerAttributes().getThrift());

        if (firepower < dataaccess.getHorde().getMagnitude()) {
            message = "Your groups firepower wasnt enough to stop the horde. Despite your best efforts, the"
                    + "overwhelming number of zombies overran your defenses.";
        }
        else if (dataaccess.getInventory().getFood() < resourcerequired) {
            message = "Your group ran out of food while holding off the horde. Starvation weakened your "
                    + "survivors, and the zombies overwhelmed them.";
        }
        else if (dataaccess.getInventory().getWater() < resourcerequired) {
            message = "Your group ran out of water during the prolonged defense. Dehydration sapped their strength, "
                    + "leaving them defenseless against the horde.";
        }
        else {
            dataaccess.setWon(true);
            message = "Your group successfully defended against the horde! With well-armed fighters, "
                    + "strong leadership, and ample supplies, you emerged victorious and kept the group safe.";
        }

        final int score = dataaccess.getPlayerInfo().getScore();
        final HordeOutputData outputdata = new HordeOutputData(score, message);
        outputboundary.prepareSuccessView(outputdata);
    }

}
