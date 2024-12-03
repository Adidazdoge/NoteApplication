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

    public AmbushEventInteractor(AmbushDataAccessInterface dataAccess,
                                 AmbushOutputBoundary outputBoundary,
                                 ChatGptService chatGptService) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
    }

    @Override
    public void execute(AmbushInputData inputData) {
        EventAmbush ambushEvent = (EventAmbush) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        int playerChoice = inputData.getChoice();

        try {
            // Determine the choice description
            String choiceDescription = ambushEvent.getchoices().get(playerChoice);

            // Call ChatGPT to generate the event outcome
            String chatResponse = chatGptService.getResponse(ambushEvent.getdescription(), playerAttributes, choiceDescription);

            // Parse the ChatGPT response
            String eventOutcome = ChatGptResponseParser.parseEventOutcome(chatResponse);

            // Apply game logic based on the player's choice
            int foodChange = 0, waterChange = 0, weaponChange = 0, peopleChange = 0;
            if (playerChoice == EntityConstants.FIRSTCHOICE) {
                // Fight back logic
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
            } else if (playerChoice == EntityConstants.SECONDCHOICE) {
                // Pay the bandits logic
                foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
            } else if (playerChoice == EntityConstants.THIRDCHOICE) {
                // Negotiate logic
                if (playerAttributes.getOrDefault("Social", 0) >= EntityConstants.AMBUSHNEGOTIATE) {
                    // Success logic
                } else {
                    foodChange = EntityConstants.AMBUSHFAILRESOURCEFOOD;
                    waterChange = EntityConstants.AMBUSHFAILRESOURCEWATER;
                    weaponChange = EntityConstants.AMBUSHFAILRESOURCEWEAPON;
                }
            } else {
                throw new IllegalArgumentException("Invalid player choice: " + playerChoice);
            }

            // Apply inventory changes
            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.changeWeapon(weaponChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            // Prepare output
            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange + ".";
            AmbushOutputData outputData = new AmbushOutputData(eventOutcome, foodChange, waterChange, weaponChange, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}