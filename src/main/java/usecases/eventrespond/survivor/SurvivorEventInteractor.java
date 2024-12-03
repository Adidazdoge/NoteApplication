package usecases.eventrespond.survivor;

import entities.EntityConstants;
import entities.EventSurvivorJoins;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Survivor Encounter event.
 * Delegates response generation to ChatGPT and applies game logic based on the response.
 */
public class SurvivorEventInteractor implements SurvivorInputBoundary {
    private final SurvivorDataAccessInterface dataAccess;
    private final SurvivorOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;
    private final ChatGptResponseParser responseParser;

    public SurvivorEventInteractor(SurvivorDataAccessInterface dataAccess,
                                   SurvivorOutputBoundary outputBoundary,
                                   ChatGptService chatGptService,
                                   ChatGptResponseParser responseParser) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
        this.responseParser = responseParser;
    }

    @Override
    public void execute(SurvivorInputData inputData) {
        EventSurvivorJoins survivorEvent = (EventSurvivorJoins) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        Map<Integer, String> choices = survivorEvent.getchoices();

        try {
            // Call ChatGPT to generate a response
            String chatResponse = chatGptService.getResponse(survivorEvent.getdescription(), playerAttributes, choices);

            // Parse the ChatGPT response
            int chosenOption = responseParser.parseChoice(chatResponse);
            String eventDescription = responseParser.parseDescription(chatResponse);

            // Handle game logic based on the chosen option
            int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;

            if (chosenOption == EntityConstants.FIRSTCHOICE) {
                // Accept survivors
                peopleChange = EntityConstants.SURVIVORACCEPTPEOPLEGAIN;
            } else if (chosenOption == EntityConstants.SECONDCHOICE) {
                // Politely reject survivors
                // No resource changes
            } else if (chosenOption == EntityConstants.THIRDCHOICE) {
                // Attempt to rob survivors
                if (dataAccess.getInventory().getfirepower() >= EntityConstants.SURVIVORROBBERYPOWER) {
                    foodChange = EntityConstants.SURVIVORROBBERYGAINFOOD;
                    suppliesChange = EntityConstants.SURVIVORROBBERYGAINSUPPLIES;
                } else {
                    foodChange = EntityConstants.SURVIVORROBBERYFAILLOSSFOOD;
                    peopleChange = EntityConstants.SURVIVORROBBERYFAILLOSSPEOPLE;
                }
            } else {
                throw new IllegalArgumentException("Invalid choice from ChatGPT: " + chosenOption);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWeapon(suppliesChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            // Prepare output
            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                    ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
            SurvivorOutputData outputData = new SurvivorOutputData(eventDescription, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}