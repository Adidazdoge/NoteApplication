package interface_adapters.eventrespond;

import entities.Event;
import entities.EventAmbush;
import entities.EventFlood;
import entities.EventBlizzard;
import entities.EventTraderEncounter;
import entities.EventSurvivorJoins;
import usecases.eventrespond.shared.*;
import usecases.eventrespond.ambush.AmbushEventInteractor;
import usecases.eventrespond.flood.FloodEventInteractor;
import usecases.eventrespond.blizzard.BlizzardEventInteractor;
import usecases.eventrespond.trader.TraderEventInteractor;
import usecases.eventrespond.survivor.SurvivorEventInteractor;

/**
 * Controller for handling event responses using a single adapter.
 * Determines the correct interactor based on the event type.
 */
public class EventResponseController {
    private final RespondDataAccessInterface dataAccess;
    private final RespondOutputBoundary outputBoundary;

    public EventResponseController(RespondDataAccessInterface dataAccess, RespondOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Handles the response to a given event by selecting the appropriate interactor.
     * @param inputData The player's input data (choice).
     */
    public void execute(RespondInputData inputData) {
        Event event = dataAccess.getevent();

        RespondInputBoundary interactor;
        if (event instanceof EventAmbush) {
            interactor = new AmbushEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventFlood) {
            interactor = new FloodEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventBlizzard) {
            interactor = new BlizzardEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventTraderEncounter) {
            interactor = new TraderEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventSurvivorJoins) {
            interactor = new SurvivorEventInteractor(dataAccess, outputBoundary);
        } else {
            outputBoundary.prepareFailureView("Unknown event type.");
            return;
        }

        interactor.execute(inputData);
    }
}
