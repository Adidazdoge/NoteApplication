package usecases.respond;

import entities.EntityConstants;
import entities.Event;
import entities.EventAmbush;
import entities.Inventory;
import entities.PlayerAttributes;
import kotlin.Pair;

/**
 * Interactor for the response use case, for the event, for the specific choice player provide.
 */
public class RespondInteractor implements RespondInputBoundary {
    private final RespondDataAccessInterface respondDataAccessObject;
    private final RespondOutputBoundary respondOutputBoundary;

    public RespondInteractor(RespondDataAccessInterface respondDataAccessObject,
                             RespondOutputBoundary respondOutputBoundary) {
        this.respondDataAccessObject = respondDataAccessObject;
        this.respondOutputBoundary = respondOutputBoundary;
    }

    @Override
    public void execute(RespondInputData inputdata) {
        final Event event = respondDataAccessObject.getevent();
        final int choice = inputdata.getChoice();
        final PlayerAttributes attributes = respondDataAccessObject.getPlayerAttributes();
        final Inventory inventory = respondDataAccessObject.getInventory();
        if (event instanceof EventAmbush) {
            final Pair<RespondOutputData, Boolean> info =
                    ambushevaluator(attributes, choice, inventory, event);
            final boolean success = info.getSecond();
            if (success) {
                respondOutputBoundary.prepareSuccessView(info.getFirst());
            }
            else {
                respondOutputBoundary.prepareFailureView("Invalid choice");
            }
        }
    }

    private Pair<RespondOutputData, Boolean> ambushevaluator(PlayerAttributes attributes,
                                                             int choice,
                                                              Inventory inventory,
                                                             Event event) {
        String message = "";
        int foodchange = 0;
        int waterchange = 0;
        int weaponchange = 0;
        int peoplechange = 0;
        boolean success = true;
        final EventAmbush ambush = (EventAmbush) event;
        if (choice == EntityConstants.FIRSTCHOICE) {
            if (inventory.getfirepower() >= EntityConstants.AMBUSHPOWER) {
                foodchange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEFOOD;
                waterchange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWATER;
                weaponchange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWEAPON;
                peoplechange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEPEOPLE;
                message = ambush.getFightoutcomesuccess();
            }
            else {
                foodchange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterchange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponchange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                peoplechange = EntityConstants.AMBUSHFAILRESOURCEPEOPLE;
                message = ambush.getFightoutcomefailed();
            }
        }
        else if (choice == EntityConstants.SECONDCHOICE) {
            foodchange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
            waterchange = EntityConstants.AMBUSHFAILRESOURCEWATER;
            weaponchange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            message = ambush.getPayoutcome();
        }
        else if (choice == EntityConstants.THIRDCHOICE) {
            if (attributes.getSocial() < EntityConstants.AMBUSHNEGOTIATE) {
                foodchange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterchange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponchange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                message = ambush.getNegotiatefailedoutcome();
            }
            else {
                message = ambush.getNegotiatesuccessoutcome();
            }
        }
        else {
            success = false;
        }
        final String inventorymessage = inventory.generateResourceChangeMessage(
                foodchange, waterchange, weaponchange, peoplechange);
        inventory.changeFood(foodchange);
        inventory.changeWater(waterchange);
        inventory.changeweapon(weaponchange);
        inventory.changePeople(peoplechange);
        return new Pair<>(
                new RespondOutputData(
                        message, foodchange, waterchange, weaponchange, peoplechange, inventorymessage
                ),
                success
        );

    }
}
