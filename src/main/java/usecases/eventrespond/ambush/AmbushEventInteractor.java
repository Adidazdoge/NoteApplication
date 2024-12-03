package usecases.eventrespond.ambush;

import entities.EntityConstants;
import entities.EventAmbush;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to an Ambush event.
 * Delegates response generation to ChatGPT and applies game logic based on the response.
 */
public class AmbushEventInteractor implements AmbushInputBoundary {
    private final AmbushDataAccessInterface dataAccess;
    private final AmbushOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;
    private final ChatGptResponseParser responseParser;

    public AmbushEventInteractor(AmbushDataAccessInterface dataAccess,
                                 AmbushOutputBoundary outputBoundary,
                                 ChatGptService chatGptService,
                                 ChatGptResponseParser responseParser) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
        this.responseParser = responseParser;
    }

    @Override
    public void execute(AmbushInputData inputData) {
        EventAmbush ambushEvent = (EventAmbush) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributes();
        Map<Integer, String> choices = ambushEvent.getchoices();

        try {
            // Call ChatGPT to generate a response
            String chatResponse = chatGptService.getResponse(ambushEvent.getdescription(), playerAttributes, choices);

            // Parse the ChatGPT response
            int chosenOption = responseParser.parseChoice(chatResponse);
            String eventDescription = responseParser.parseDescription(chatResponse);

            // Handle game logic based on the chosen option
            int foodChange = 0, waterChange = 0, weaponChange = 0, peopleChange = 0;

            if (chosenOption == EntityConstants.FIRSTCHOICE) {
                // Fight back
                if (dataAccess.getInventory().getfirepower() >= EntityConstants.AMBUSHPOWER) {
                    foodChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEFOOD;
                    waterChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWATER;
                    weaponChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEWEAPON;
                    peopleChange = EntityConstants.AMBUSHFIGHTSUCCESSRESOURCEPEOPLE;
                } else {
                    foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                    waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                    weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                    peopleChange = EntityConstants.AMBUSHFAILRESOURCEPEOPLE;
                }
            } else if (chosenOption == EntityConstants.SECONDCHOICE) {
                // Pay the bandits
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            } else if (chosenOption == EntityConstants.THIRDCHOICE) {
                // Negotiate
                if (playerAttributes.getOrDefault("Social", 0) >= EntityConstants.AMBUSHNEGOTIATE) {
                    // Successful negotiation
                } else {
                    foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                    waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                    weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                }
            } else {
                throw new IllegalArgumentException("Invalid choice from ChatGPT: " + chosenOption);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.changeWeapon(weaponChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            // Prepare output
            AmbushOutputData outputData = new AmbushOutputData(eventDescription, foodChange, waterChange, weaponChange, peopleChange, "Resources updated.");
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}