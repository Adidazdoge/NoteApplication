package interface_adapters.endprocesshorde;

import usecases.endprocesshorde.HordeInputBoundary;
import usecases.endprocesshorde.HordeInputData;

/**
 * Horde controller, for horde process use case.
 */
public class HordeController {
    private final HordeInputBoundary interactor;

    public HordeController(HordeInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute this horde usecase.
     */
    public void execute() {
        final HordeInputData inputData = new HordeInputData();
        interactor.execute(inputData);
    }
}
