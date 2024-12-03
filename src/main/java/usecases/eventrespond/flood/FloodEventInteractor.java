package usecases.eventrespond.flood;

import entities.EntityConstants;
import entities.EventFlood;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Flood event.
 * Delegates response generation to ChatGPT and applies game logic based on the response.
 */
public class FloodEventInteractor implements FloodInputBoundary {
    private final FloodDataAccessInterface dataAccess;
    private final FloodOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;
    private final ChatGptResponseParser responseParser;

    public FloodEventInteractor(FloodDataAccessInterface dataAccess,
                                FloodOutputBoundary outputBoundary,
                                ChatGptService chatGptService,
                                ChatGptResponseParser responseParser) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
        this.responseParser = responseParser;
    }

    @Override
    public void execute(FloodInputData inputData) {
        EventFlood floodEvent = (EventFlood) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        Map<Integer, String> choices = floodEvent.getchoices();

        try {
            // Call ChatGPT to generate a response
            String chatResponse = chatGptService.getResponse(floodEvent.getdescription(), playerAttributes, choices);

            // Parse the ChatGPT response
            int chosenOption = responseParser.parseChoice(chatResponse);
            String eventDescription = responseParser.parseDescription(chatResponse);

            // Handle game logic based on the chosen option
            int foodChange = 0, waterChange = 0, peopleChange = 0;

            if (chosenOption == EntityConstants.FIRSTCHOICE) {
                // Evacuate to higher ground
                foodChange = EntityConstants.FLOODRESOURCELOSSFOOD;
                peopleChange = EntityConstants.FLOODPEOPLELOSSLOW;
            } else if (chosenOption == EntityConstants.SECONDCHOICE) {
                // Secure supplies and hold position
                foodChange = EntityConstants.FLOODRESOURCELOSSSECURE;
                peopleChange = EntityConstants.FLOODPEOPLELOSSMODERATE;
            } else if (chosenOption == EntityConstants.THIRDCHOICE) {
                // Do nothing
                foodChange = EntityConstants.FLOODRESOURCELOSSHIGH;
                peopleChange = EntityConstants.FLOODPEOPLELOSSHIGH;
            } else {
                throw new IllegalArgumentException("Invalid choice from ChatGPT: " + chosenOption);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", People " + peopleChange + ".";
        FloodOutputData outputData = new FloodOutputData(eventDescription, foodChange, waterChange, 0, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}