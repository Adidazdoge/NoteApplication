package usecases.eventrespond;

import entities.EntityConstants;
import entities.Event;
import entities.EventAmbush;
import entities.EventBlizzard;
import entities.EventFlood;
import entities.EventTraderEncounter;
import entities.Inventory;
import entities.PlayerAttributes;
import kotlin.Pair;

import java.util.Random;

/**
 * Interactor for the response use case, handling events based on player choices.
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
            final Pair<RespondOutputData, Boolean> info = ambushEvaluator(attributes, choice, inventory, event);
            processResult(info);
        } else if (event instanceof EventFlood) {
            final Pair<RespondOutputData, Boolean> info = floodEvaluator(choice, inventory, event);
            processResult(info);
        } else if (event instanceof EventBlizzard) {
            final Pair<RespondOutputData, Boolean> info = blizzardEvaluator(inventory, event);
            processResult(info);
        } else if (event instanceof EventTraderEncounter) {
            final Pair<RespondOutputData, Boolean> info = traderEvaluator(attributes, choice, inventory, event);
            processResult(info);
        }
    }

    private void processResult(Pair<RespondOutputData, Boolean> info) {
        if (info.getSecond()) {
            respondOutputBoundary.prepareSuccessView(info.getFirst());
        } else {
            respondOutputBoundary.prepareFailureView("Invalid choice");
        }
    }

    private Pair<RespondOutputData, Boolean> ambushEvaluator(PlayerAttributes attributes,
                                                             int choice,
                                                             Inventory inventory,
                                                             Event event) {
        String message = "";
        int foodChange = 0;
        int waterChange = 0;
        int weaponChange = 0;
        int peopleChange = 0;
        boolean success = true;
        final EventAmbush ambush = (EventAmbush) event;

        if (choice == EntityConstants.FIRSTCHOICE) {
            if (inventory.getfirepower() >= EntityConstants.AMBUSHPOWER) {
                foodChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEPEOPLE;
                message = ambush.getFightoutcomesuccess();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                peopleChange = EntityConstants.AMBUSHFAILRESOURCEPEOPLE;
                message = ambush.getFightoutcomefailed();
            }
        } else if (choice == EntityConstants.SECONDCHOICE) {
            foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
            waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
            weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            message = ambush.getPayoutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) {
            if (attributes.getSocial() >= EntityConstants.AMBUSHNEGOTIATE) {
                message = ambush.getNegotiatesuccessoutcome();
            } else {
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                message = ambush.getNegotiatefailedoutcome();
            }
        } else {
            success = false;
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);
        inventory.changeweapon(weaponChange);
        inventory.changePeople(peopleChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, weaponChange, peopleChange, ""), success);
    }

    private Pair<RespondOutputData, Boolean> floodEvaluator(int choice, Inventory inventory, Event event) {
        String message;
        int foodChange = 0;
        boolean success = true;

        EventFlood flood = (EventFlood) event;
        if (choice == EntityConstants.FIRSTCHOICE) {
            foodChange = EntityConstants.FLOODRESOURCELOSS;
            message = flood.getEvacuateOutcome();
        } else if (choice == EntityConstants.SECONDCHOICE) {
            foodChange = EntityConstants.FLOODSECURELOSS;
            message = flood.getSecureSuppliesOutcome();
        } else if (choice == EntityConstants.THIRDCHOICE) {
            foodChange = EntityConstants.FLOODDOINGNOTHINGLOSS;
            message = flood.getDoNothingOutcome();
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        return new Pair<>(new RespondOutputData(message, foodChange, 0, 0, 0, ""), success);
    }

    private Pair<RespondOutputData, Boolean> blizzardEvaluator(Inventory inventory, Event event) {
        String message = ((EventBlizzard) event).getOutcome
        inventory.changeFood(Entityconstants.BLIZZARDRESOURCELOSSFOOD);
        inventory.changeWater(Entityconstants.BLIZZARDRESOURCELOSSWATER);

        return new Pair<>(new RespondOutputData(message, Entityconstants.BLIZZARDRESOURCELOSSFOOD, Entityconstants.BLIZZARDRESOURCELOSSWATER, 0, 0, ""), true);
    }

    private Pair<RespondOutputData, Boolean> traderEvaluator(PlayerAttributes attributes, int choice, Inventory inventory, Event event) {
        String message;
        int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;
        boolean success = true;

        EventTraderEncounter trader = (EventTraderEncounter) event;
        if (choice == Entityconstants.FIRSTCHOICE) {
            if (attributes.getSocial() >= Entityconstants.TRADERNEGOTIATE) {
                foodChange = Entityconstants.TRADERTRADEGAINFOOD;
                waterChange = Entityconstants.TRADERTRADEGAINWATER;
                message = trader.getTradeOutcomeSuccess();
            } else {
                foodChange = Entityconstants.TRADERTRADEFAILLOSSFOOD;
                waterChange = Entityconstants.TRADERTRADEFAILLOSSWATER;
                message = trader.getTradeOutcomeScam();
            }
        } else if (choice == Entityconstants.SECONDCHOICE) {
            message = trader.getIgnoreOutcome();
        } else if (choice == Entityconstants.THIRDCHOICE) {
            if (inventory.getfirepower() >= Entityconstants.TRADERROBBERYPOWER) {
                foodChange = Entityconstants.TRADERROBBERYGAINFOOD;
                suppliesChange = Entityconstants.TRADERROBBERYGAINSUPPLIES;
                message = trader.getRobOutcomeSuccess();
            } else {
                foodChange = Entityconstants.TRADERROBBERYFAILLOSSFOOD;
                peopleChange = Entityconstants.TRADERROBBERYFAILLOSSPEOPLE;
                message = trader.getRobOutcomeFail();
            }
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);
        inventory.changeweapon(suppliesChange);
        inventory.changePeople(peopleChange);

        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, suppliesChange, peopleChange, ""), success);
    }
}
