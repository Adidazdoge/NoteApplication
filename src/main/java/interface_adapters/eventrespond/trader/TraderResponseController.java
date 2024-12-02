package interface_adapters.eventrespond.trader;

import usecases.eventrespond.trader.TraderEventInteractor;
import usecases.eventrespond.shared.RespondInputData;

/**
 * Controller for handling responses to a Trader event.
 */
public class TraderResponseController {
    private final TraderEventInteractor interactor;

    public TraderResponseController(TraderEventInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the interactor by converting the player’s input into input data.
     * @param choice The player's choice for the event.
     */
    public void execute(int choice) {
        RespondInputData inputData = new RespondInputData(choice);
        interactor.execute(inputData);
    }
}