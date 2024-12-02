package usecases.eventrespond.ambush;

import entities.EntityConstants;
import entities.EventAmbush;
import usecases.eventrespond.ambush.AmbushInputData;
import usecases.eventrespond.ambush.AmbushOutputBoundary;
import usecases.eventrespond.ambush.AmbushDataAccessInterface;

/**
 * Interactor for handling player responses to an Ambush event.
 * Implements the AmbushInputBoundary interface.
 */
public class AmbushEventInteractor implements AmbushInputBoundary {
    private final AmbushDataAccessInterface dataAccess;
    private final AmbushOutputBoundary outputBoundary;

    public AmbushEventInteractor(AmbushDataAccessInterface dataAccess, AmbushOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(AmbushInputData inputData) {
        EventAmbush ambushEvent = (EventAmbush) dataAccess.getEvent();
        int choice = inputData.getChoice();

        int foodChange = 0;
        int waterChange = 0;
        int weaponChange = 0;
        int peopleChange = 0;
        String message;

        if (choice == EntityConstants.FIRSTCHOICE) {
            // Fight back
            if (dataAccess.getInventory().getfirepower() >= EntityConstants.AMBUSHPOWER) {
                foodChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEPEOPLE;
                message = ambushEvent.getFightoutcomesuccess();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFAILRESOURCEPEOPLE;
                message = ambushEvent.getFightoutcomefailed();
            }
        } else if (choice == EntityConstants.SECONDCHOICE) {
            // Pay the bandits
            foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
            waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
            weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            message = ambushEvent.getPayoutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) {
            // Negotiate
            if (dataAccess.getPlayerAttributes().getSocial() >= EntityConstants.AMBUSHNEGOTIATE) {
                message = ambushEvent.getNegotiatesuccessoutcome();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                message = ambushEvent.getNegotiatefailedoutcome();
            }
        } else {
            // Invalid choice
            outputBoundary.prepareFailureView("Invalid choice provided.");
            return;
        }

        // Apply changes to inventory
        dataAccess.changeFood(foodChange);
        dataAccess.changeWater(waterChange);
        dataAccess.changeWeapon(weaponChange);
        dataAccess.changePeople(peopleChange);

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", Weapons " + weaponChange + ", People " + peopleChange + ".";
        AmbushOutputData outputData = new AmbushOutputData(message, foodChange, waterChange, weaponChange, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
