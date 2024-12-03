package usecases.eventrespond.survivor;

import entities.EntityConstants;
import entities.EventSurvivorJoins;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Survivor event.
 */
public class SurvivorEventInteractor implements SurvivorInputBoundary {
    private final SurvivorDataAccessInterface dataAccess;
    private final SurvivorOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;

    public SurvivorEventInteractor(SurvivorDataAccessInterface dataAccess,
                                   SurvivorOutputBoundary outputBoundary,
                                   ChatGptService chatGptService) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
    }

    @Override
    public void execute(SurvivorInputData inputData) {
        EventSurvivorJoins survivorEvent = (EventSurvivorJoins) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        int playerChoice = inputData.getChoice();

        try {
            String choiceDescription = survivorEvent.getchoices().get(playerChoice);

            String chatResponse = chatGptService.getResponse(survivorEvent.getdescription(), playerAttributes, choiceDescription);

            String eventOutcome = ChatGptResponseParser.parseEventOutcome(chatResponse);

            int foodChange = 0, waterChange = 0, suppliesChange = 0, peopleChange = 0;

            if (playerChoice == EntityConstants.FIRSTCHOICE) {
                peopleChange = EntityConstants.SURVIVORACCEPTPEOPLEGAIN;
            } else if (playerChoice == EntityConstants.SECONDCHOICE) {
                // Reject survivors, no changes needed
            } else if (playerChoice == EntityConstants.THIRDCHOICE) {
                if (dataAccess.getInventory().getfirepower() >= EntityConstants.SURVIVORROBBERYPOWER) {
                    // Gain resources logic
                } else {
                    // Robbery fail logic
                }
            } else {
                throw new IllegalArgumentException("Invalid player choice: " + playerChoice);
            }

            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

            String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                    ", Supplies " + suppliesChange + ", People " + peopleChange + ".";
            SurvivorOutputData outputData = new SurvivorOutputData(eventOutcome, foodChange, waterChange, suppliesChange, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}