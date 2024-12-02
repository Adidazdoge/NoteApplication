package usecases.eventrespond.flood;

import entities.EntityConstants;
import entities.EventFlood;
import usecases.eventrespond.flood.FloodInputData;
import usecases.eventrespond.flood.FloodOutputBoundary;
import usecases.eventrespond.flood.FloodDataAccessInterface;

/**
 * Interactor for handling player responses to a Flood event.
 * Implements the FloodInputBoundary interface.
 */
public class FloodEventInteractor implements FloodInputBoundary {
    private final FloodDataAccessInterface dataAccess;
    private final FloodOutputBoundary outputBoundary;

    public FloodEventInteractor(FloodDataAccessInterface dataAccess, FloodOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(FloodInputData inputData) {
        EventFlood floodEvent = (EventFlood) dataAccess.getEvent();
        int choice = inputData.getChoice();

        int foodChange = 0, waterChange = 0, peopleChange = 0;
        String message;

        switch (choice) {
            case EntityConstants.FIRSTCHOICE: // Evacuate to higher ground
                foodChange = EntityConstants.FLOODRESOURCELOSSFOOD;
                peopleChange = EntityConstants.FLOODPEOPLELOSSLOW;
                message = floodEvent.getEvacuateOutcome();
                break;
            case EntityConstants.SECONDCHOICE: // Secure supplies and hold position
                foodChange = EntityConstants.FLOODRESOURCELOSSSECURE;
                peopleChange = EntityConstants.FLOODPEOPLELOSSMODERATE;
                message = floodEvent.getSecureSuppliesOutcome();
                break;
            case EntityConstants.THIRDCHOICE: // Do nothing
                foodChange = EntityConstants.FLOODRESOURCELOSSHIGH;
                peopleChange = EntityConstants.FLOODPEOPLELOSSHIGH;
                message = floodEvent.getDoNothingOutcome();
                break;
            default: // Invalid choice
                outputBoundary.prepareFailureView("Invalid choice provided.");
                return;
        }

        // Apply changes to inventory
        dataAccess.changeFood(foodChange);
        dataAccess.changeWater(waterChange);
        dataAccess.changePeople(peopleChange);

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", People " + peopleChange + ".";
        FloodOutputData outputData = new FloodOutputData(message, foodChange, waterChange, 0, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
