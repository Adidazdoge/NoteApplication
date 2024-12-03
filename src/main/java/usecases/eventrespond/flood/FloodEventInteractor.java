package usecases.eventrespond.flood;

import entities.EntityConstants;
import entities.EventFlood;
import usecases.chatgpt.ChatGptService;
import usecases.chatgpt.ChatGptResponseParser;

import java.util.Map;

/**
 * Interactor for handling player responses to a Flood event.
 */
public class FloodEventInteractor implements FloodInputBoundary {
    private final FloodDataAccessInterface dataAccess;
    private final FloodOutputBoundary outputBoundary;
    private final ChatGptService chatGptService;

    public FloodEventInteractor(FloodDataAccessInterface dataAccess,
                                FloodOutputBoundary outputBoundary,
                                ChatGptService chatGptService) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.chatGptService = chatGptService;
    }

    @Override
    public void execute(FloodInputData inputData) {
        EventFlood floodEvent = (EventFlood) dataAccess.getEvent();
        Map<String, Integer> playerAttributes = dataAccess.getPlayerAttributesAsMap();
        int playerChoice = inputData.getChoice();

        try {
            String choiceDescription = floodEvent.getchoices().get(playerChoice);

            String chatResponse = chatGptService.getResponse(floodEvent.getdescription(), playerAttributes, choiceDescription);

            String eventOutcome = ChatGptResponseParser.parseEventOutcome(chatResponse);

            int foodChange = 0, waterChange = 0, peopleChange = 0;

            if (playerChoice == EntityConstants.FIRSTCHOICE) {
                foodChange = EntityConstants.FLOODRESOURCELOSSFOOD;
                peopleChange = EntityConstants.FLOODPEOPLELOSSLOW;
            } else if (playerChoice == EntityConstants.SECONDCHOICE) {
                foodChange = EntityConstants.FLOODRESOURCELOSSSECURE;
                peopleChange = EntityConstants.FLOODPEOPLELOSSMODERATE;
            } else if (playerChoice == EntityConstants.THIRDCHOICE) {
                foodChange = EntityConstants.FLOODRESOURCELOSSHIGH;
                peopleChange = EntityConstants.FLOODPEOPLELOSSHIGH;
            } else {
                throw new IllegalArgumentException("Invalid player choice: " + playerChoice);
            }

            dataAccess.changeFood(foodChange);
            dataAccess.changeWater(waterChange);
            dataAccess.changePeople(peopleChange);
            dataAccess.removeEvent();

        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", People " + peopleChange + ".";
        FloodOutputData outputData = new FloodOutputData(eventOutcome, foodChange, waterChange, 0, peopleChange, inventoryMessage);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            outputBoundary.prepareFailureView("Failed to process ChatGPT response: " + e.getMessage());
        }
    }
}