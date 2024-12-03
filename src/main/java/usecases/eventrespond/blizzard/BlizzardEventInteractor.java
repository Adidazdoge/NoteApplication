package usecases.eventrespond.blizzard;

import entities.EntityConstants;
import entities.EventBlizzard;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Blizzard event.
 */
public class BlizzardEventInteractor implements BlizzardInputBoundary {
    private final BlizzardDataAccessInterface dataAccess;
    private final BlizzardOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;

    public BlizzardEventInteractor(BlizzardDataAccessInterface dataAccess,
                                   BlizzardOutputBoundary outputBoundary,
                                   ChatGptService chatGptService) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
    }

    @Override
    public void execute(BlizzardInputData inputData) {
        EventBlizzard blizzardEvent = (EventBlizzard) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        int playerChoice = inputData.getChoice();

        try {
            String choiceDescription = blizzardEvent.getchoices().get(playerChoice);

            String chatResponse = chatGptService.getResponse(blizzardEvent.getdescription(), playerAttributes, choiceDescription);

            String eventOutcome = ChatGptResponseParser.parseEventOutcome(chatResponse);

            int foodChange = 0, waterChange = 0;

            if (playerChoice == EntityConstants.FIRSTCHOICE) {
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD / 2;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER / 2;
            } else if (playerChoice == EntityConstants.SECONDCHOICE) {
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER;
            } else if (playerChoice == EntityConstants.THIRDCHOICE) {
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD * 2;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER * 2;
            } else {
                throw new IllegalArgumentException("Invalid player choice: " + playerChoice);
            }

            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.removeEvent();

            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange + ".";
            BlizzardOutputData outputData = new BlizzardOutputData(eventOutcome, foodChange, waterChange, 0, 0, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}