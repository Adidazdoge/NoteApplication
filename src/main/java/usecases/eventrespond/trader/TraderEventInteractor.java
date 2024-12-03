package usecases.eventrespond.trader;

import entities.EntityConstants;
import entities.EventTraderEncounter;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Trader event.
 */
public class TraderEventInteractor implements TraderInputBoundary {
    private final TraderDataAccessInterface dataAccess;
    private final TraderOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;

    public TraderEventInteractor(TraderDataAccessInterface dataAccess,
                                 TraderOutputBoundary outputBoundary,
                                 ChatGptService chatGptService) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
    }

    @Override
    public void execute(TraderInputData inputData) {
        EventTraderEncounter traderEvent = (EventTraderEncounter) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        int playerChoice = inputData.getChoice();

        try {
            String choiceDescription = traderEvent.getchoices().get(playerChoice);

            String chatResponse = chatGptService.getResponse(traderEvent.getdescription(), playerAttributes, choiceDescription);

            String eventOutcome = ChatGptResponseParser.parseEventOutcome(chatResponse);

            int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;

            if (playerChoice == EntityConstants.FIRSTCHOICE) {
                // Trade logic
            } else if (playerChoice == EntityConstants.SECONDCHOICE) {
                // Ignore logic
            } else if (playerChoice == EntityConstants.THIRDCHOICE) {
                // Rob logic
            } else {
                throw new IllegalArgumentException("Invalid player choice: " + playerChoice);
            }

            dataAccess.changeFood(foodChange);
            dataAccess.changeWeapon(suppliesChange);
            dataAccess.removeEvent();

            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                    ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
            TraderOutputData outputData = new TraderOutputData(eventOutcome, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}