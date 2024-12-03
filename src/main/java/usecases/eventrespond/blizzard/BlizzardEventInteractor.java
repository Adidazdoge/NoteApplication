package usecases.eventrespond.blizzard;

import entities.EntityConstants;
import entities.EventBlizzard;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Blizzard event.
 * Delegates response generation to ChatGPT and applies game logic based on the response.
 */
public class BlizzardEventInteractor implements BlizzardInputBoundary {
    private final BlizzardDataAccessInterface dataAccess;
    private final BlizzardOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;
    private final ChatGptResponseParser responseParser;

    public BlizzardEventInteractor(BlizzardDataAccessInterface dataAccess,
                                   BlizzardOutputBoundary outputBoundary,
                                   ChatGptService chatGptService,
                                   ChatGptResponseParser responseParser) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
        this.responseParser = responseParser;
    }

    @Override
    public void execute(BlizzardInputData inputData) {
        EventBlizzard blizzardEvent = (EventBlizzard) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributes();
        Map<Integer, String> choices = blizzardEvent.getchoices();

        try {
            // Call ChatGPT to generate a response
            String chatResponse = chatGptService.getResponse(blizzardEvent.getdescription(), playerAttributes, choices);

            // Parse the ChatGPT response
            int chosenOption = responseParser.parseChoice(chatResponse);
            String eventDescription = responseParser.parseDescription(chatResponse);

            // Handle game logic based on the chosen option
            int foodChange = 0, waterChange = 0;

            if (chosenOption == EntityConstants.FIRSTCHOICE) {
                // Secure shelter
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD / 2;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER / 2;
            } else if (chosenOption == EntityConstants.SECONDCHOICE) {
                // Prepare supplies
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER;
            } else if (chosenOption == EntityConstants.THIRDCHOICE) {
                // Do nothing
                foodChange = EntityConstants.BLIZZARDRESOURCELOSSFOOD * 2;
                waterChange = EntityConstants.BLIZZARDRESOURCELOSSWATER * 2;
            } else {
                throw new IllegalArgumentException("Invalid choice from ChatGPT: " + chosenOption);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.removeEvent();

            // Prepare output
            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange + ".";
            BlizzardOutputData outputData = new BlizzardOutputData(eventDescription, foodChange, waterChange, 0, 0, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}