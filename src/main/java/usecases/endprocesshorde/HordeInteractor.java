package usecases.endprocesshorde;

import entities.EntityConstants;
import entities.Inventory;
import entities.PlayerAttributes;

/**
 * Interactor of horde processing, main logic happening.
 */
public class HordeInteractor implements HordeInputBoundary {
    private HordeDataAccessInterface dataAccess;
    private HordeOutputBoundary outputBoundary;

    public HordeInteractor(HordeDataAccessInterface dataaccess, HordeOutputBoundary outputboundary) {
        this.dataAccess = dataaccess;
        this.outputBoundary = outputboundary;
    }

    @Override
    public void execute(HordeInputData inputData) {
        String message = "";
        final Inventory peopleInventory = dataAccess.getInventory();
        final int people = peopleInventory.getPeople();
        final Inventory weaponInventory = dataAccess.getInventory();
        final int weapon = weaponInventory.getWeapon();
        final PlayerAttributes playerAttributes = dataAccess.getPlayerAttributes();
        final int generalship = playerAttributes.getGeneralship();

        final int armed = Math.min(weapon, people);
        final double generalshipBonus = 1 + (generalship * 0.05);
        final double firepower = armed * EntityConstants.ARMEDPEOPLEPOWER * generalshipBonus;
        final double duration = dataAccess.getHorde().getDuration();
        final double peopleAmmount = dataAccess.getInventory().getPeople();
        final double thrift = dataAccess.getPlayerAttributes().getThrift();
        final double resourcerequired = duration * peopleAmmount
                * (1 - EntityConstants.THRIFTIMPACTRESOURCELOSS * thrift);
        final int hordeMagnitude = dataAccess.getHorde().getMagnitude();
        final int food = dataAccess.getInventory().getFood();
        final int water = dataAccess.getInventory().getWater();

        if (firepower < hordeMagnitude) {
            message = "Your groups firepower wasnt enough to stop the horde. Despite your best efforts, the"
                    + "overwhelming number of zombies overran your defenses.";
        }
        else if (food < resourcerequired) {
            message = "Your group ran out of food while holding off the horde. Starvation weakened your "
                    + "survivors, and the zombies overwhelmed them.";
        }
        else if (water < resourcerequired) {
            message = "Your group ran out of water during the prolonged defense. Dehydration sapped their strength, "
                    + "leaving them defenseless against the horde.";
        }
        else {
            dataAccess.setWon(true);
            message = "Your group successfully defended against the horde! With well-armed fighters, "
                    + "strong leadership, and ample supplies, you emerged victorious and kept the group safe.";
        }

        final int score = dataAccess.getPlayerInfo().getScore();
        final HordeOutputData outputdata = new HordeOutputData(score, message);
        outputBoundary.prepareSuccessView(outputdata);
    }

}
