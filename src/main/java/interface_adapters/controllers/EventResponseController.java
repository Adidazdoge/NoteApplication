package interface_adapters.controllers;

import entities.Event;
import entities.EventAmbush;
import entities.EventTraderEncounter;
import entities.EventBlizzard;
import usecases.eventrespond.RespondInputBoundary;
import usecases.eventrespond.RespondInputData;
import usecases.eventrespond.ambush.AmbushEventInteractor;
import usecases.eventrespond.trader.TraderEventInteractor;
import usecases.eventrespond.blizzard.BlizzardEventInteractor;
import usecases.eventrespond.RespondDataAccessInterface;
import usecases.eventrespond.RespondOutputBoundary;

/**
 * Controller for handling event responses and delegating to the correct interactor.
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
    public void handleResponse(RespondInputData inputData) {
        Event event = dataAccess.getevent();

        RespondInputBoundary interactor;
        if (event instanceof EventAmbush) {
            interactor = new AmbushEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventTraderEncounter) {
            interactor = new TraderEventInteractor(dataAccess, outputBoundary);
        } else if (event instanceof EventBlizzard) {
            interactor = new BlizzardEventInteractor(dataAccess, outputBoundary);
        } else {
            outputBoundary.prepareFailureView("Unknown event type.");
            return;
        }

        interactor.execute(inputData);
    }
}
