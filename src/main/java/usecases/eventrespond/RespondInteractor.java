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
            final Pair<RespondOutputData, Boolean> info = ambushevaluator(attributes, choice, inventory, event);
            processResult(info);
        } else if (event instanceof EventFlood) {
            final Pair<RespondOutputData, Boolean> info = floodevaluator(choice, inventory, event);
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

    private Pair<RespondOutputData, Boolean> floodevaluator(int choice, Inventory inventory, Event event) {
        String message;
        int foodChange = 0;
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

        EventFlood flood = (EventFlood) event;
        if (choice == Entityconstants.FIRSTCHOICE) {
            foodChange = Entityconstants.FLOODRESOURCELOSS;
            message = flood.getEvacuateOutcome();
        } else if (choice == Entityconstants.SECONDCHOICE) {
            foodChange = Entityconstants.FLOODSECURELOSS;
            message = flood.getSecureSuppliesOutcome();
        } else if (choice == Entityconstants.THIRDCHOICE) {
            foodChange = Entityconstants.FLOODDOINGNOTHINGLOSS;
            message = flood.getDoNothingOutcome();
        } else {
            success = false;
            message = "Invalid choice.";
        }

        inventory.changeFood(foodChange);
        return new Pair<>(new RespondOutputData(message, foodChange, 0, 0, 0, ""), success);
    }

    private Pair<RespondOutputData, Boolean> blizzardEvaluator(Inventory inventory, Event event) {
        String message = ((EventBlizzard) event).getOutcome();
        inventory.changeFood(Entityconstants.BLIZZARDRESOURCELOSSFOOD);
        inventory.changeWater(Entityconstants.BLIZZARDRESOURCELOSSWATER);
        return new Pair<>(new RespondOutputData(message, -15, -15, 0, 0, ""), true);
    }

    private Pair<RespondOutputData, Boolean> traderEvaluator(PlayerAttributes attributes, int choice, Inventory inventory, Event event) {
        String message;
        int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;
        boolean success = true;

        EventTraderEncounter trader = (EventTraderEncounter) event;
        if (choice == Entityconstants.FIRSTCHOICE) {
            // Trading logic based on negotiation skill
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
            // Ignore the trader, no resource changes
            message = trader.getIgnoreOutcome();
        } else if (choice == Entityconstants.THIRDCHOICE) {
            // Robbery logic based on firepower
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
            // Invalid choice handling
            success = false;
            message = "Invalid choice.";
        }

        // Apply changes to the inventory
        inventory.changeFood(foodChange);
        inventory.changeWater(waterChange);
        inventory.changeweapon(suppliesChange);
        inventory.changePeople(peopleChange);

        // Generate response
        return new Pair<>(new RespondOutputData(message, foodChange, waterChange, suppliesChange, peopleChange, ""), success);
    }

}
