package usecases.eventrespond.trader;

import entities.EntityConstants;
import entities.EventTraderEncounter;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Trader Encounter event.
 * Delegates response generation to ChatGPT and applies game logic based on the response.
 */
public class TraderEventInteractor implements TraderInputBoundary {
    private final TraderDataAccessInterface dataAccess;
    private final TraderOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;
    private final ChatGptResponseParser responseParser;

    public TraderEventInteractor(TraderDataAccessInterface dataAccess,
                                 TraderOutputBoundary outputBoundary,
                                 ChatGptService chatGptService,
                                 ChatGptResponseParser responseParser) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
        this.responseParser = responseParser;
    }

    @Override
    public void execute(TraderInputData inputData) {
        EventTraderEncounter traderEvent = (EventTraderEncounter) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        Map<Integer, String> choices = traderEvent.getchoices();

        try {
            // Call ChatGPT to generate a response
            String chatResponse = chatGptService.getResponse(traderEvent.getdescription(), playerAttributes, choices);

            // Parse the ChatGPT response
            int chosenOption = responseParser.parseChoice(chatResponse);
            String eventDescription = responseParser.parseDescription(chatResponse);

            // Handle game logic based on the chosen option
            int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;

            if (chosenOption == EntityConstants.FIRSTCHOICE) {
                // Trade with the trader
                if (dataAccess.getPlayerAttributes().getSocial() >= EntityConstants.TRADERNEGOTIATE) {
                    foodChange = EntityConstants.TRADERTRADEGAINFOOD;
                    waterChange = EntityConstants.TRADERTRADEGAINWATER;
                } else {
                    foodChange = EntityConstants.TRADERTRADEFAILLOSSFOOD;
                    waterChange = EntityConstants.TRADERTRADEFAILLOSSWATER;
                }
            } else if (chosenOption == EntityConstants.SECONDCHOICE) {
                // Ignore the trader
                // No changes to resources
            } else if (chosenOption == EntityConstants.THIRDCHOICE) {
                // Rob the trader
                if (dataAccess.getInventory().getfirepower() >= EntityConstants.TRADERROBBERYPOWER) {
                    foodChange = EntityConstants.TRADERROBBERYGAINFOOD;
                    suppliesChange = EntityConstants.TRADERROBBERYGAINSUPPLIES;
                } else {
                    foodChange = EntityConstants.TRADERROBBERYFAILLOSSFOOD;
                    peopleChange = EntityConstants.TRADERROBBERYFAILLOSSPEOPLE;
                }
            } else {
                throw new IllegalArgumentException("Invalid choice from ChatGPT: " + chosenOption);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.changeWeapon(suppliesChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            // Prepare output
            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                    ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
            TraderOutputData outputData = new TraderOutputData(eventDescription, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}