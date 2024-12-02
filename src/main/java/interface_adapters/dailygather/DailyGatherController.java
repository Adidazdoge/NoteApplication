package interface_adapters.dailygather;

import usecases.dailygather.GatherInputBoundary;
import usecases.dailygather.GatherInputData;

/**
 * Controller of gather usecase.
 */
public class DailyGatherController {
    private GatherInputBoundary gatherUsecaseInteractor;

    public DailyGatherController(GatherInputBoundary gatherUsecaseInteractor) {
        this.gatherUsecaseInteractor = gatherUsecaseInteractor;
    }

    /**
     * Execute and call the interactor in gather.
     */
    public void execute() {
        final GatherInputData inputdata = new GatherInputData();
        gatherUsecaseInteractor.execute(inputdata);
    }
}
