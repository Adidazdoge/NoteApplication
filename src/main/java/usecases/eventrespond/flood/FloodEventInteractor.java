package usecases.eventrespond.flood;

import entities.EntityConstants;
import entities.EventFlood;
import usecases.eventrespond.shared.*;

/**
 * Interactor for handling player responses to a Flood event.
 * Implements the RespondInputBoundary interface.
 */
public class FloodEventInteractor implements RespondInputBoundary {
    private final RespondDataAccessInterface dataAccess;
    private final RespondOutputBoundary outputBoundary;

    public FloodEventInteractor(RespondDataAccessInterface dataAccess, RespondOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RespondInputData inputData) {
        EventFlood floodEvent = (EventFlood) dataAccess.getevent();
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
        dataAccess.changefood(foodChange);
        dataAccess.changewater(waterChange);
        dataAccess.changepeople(peopleChange);

        // Prepare output
        String inventoryMessage = "Resources changed: Food " + foodChange + ", Water " + waterChange +
                                  ", People " + peopleChange + ".";
        RespondOutputData outputData = new RespondOutputData(message, foodChange, waterChange, 0, peopleChange, inventoryMessage);
        outputBoundary.prepareSuccessView(outputData);
    }
}
