package usecases.eventrespond.ambush;

import entities.EntityConstants;
import entities.EventAmbush;
import entities.Inventory;
import entities.PlayerAttributes;
import kotlin.Pair;
import usecases.eventrespond.RespondOutputData;

/**
 * Handles responses to the Ambush event.
 */
public class AmbushInteractor {

    public Pair<RespondOutputData, Boolean> evaluate(PlayerAttributes attributes, int choice, Inventory inventory, EventAmbush event) {
        String message = "";
        int foodChange = 0, waterChange = 0, weaponChange = 0, peopleChange = 0;
        boolean success = true;

        if (choice == EntityConstants.FIRSTCHOICE) { // Fight
            if (inventory.getfirepower() >= EntityConstants.AMBUSHPOWER) {
                foodChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEPEOPLE;
                message = event.getFightoutcomesuccess();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFAILRESOURCEPEOPLE;
                message = event.getFightoutcomefailed();
            }
        } else if (choice == EntityConstants.SECONDCHOICE) { // Negotiate
            if (attributes.getSocial() >= EntityConstants.AMBUSHNEGOTIATE) {
                message = event.getNegotiatesuccessoutcome();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                message = event.getNegotiatefailedoutcome();
            }
        } else if (choice == EntityConstants.THIRDCHOICE) { // Flee
            foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
            waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
            weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            peopleChange = -1; // Lose a group member while fleeing
            message = "You fled from the ambush, losing supplies and a group member.";
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);
        inventory.changeweapon(weaponChange);
        inventory.changePeople(peopleChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, weaponChange, peopleChange, ""), success);
    }
}
