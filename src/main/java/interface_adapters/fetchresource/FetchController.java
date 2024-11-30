package interface_adapters.fetchresource;

import usecases.fetchresource.FetchInputBoundary;
import usecases.fetchresource.FetchInputData;

/**
 * Fetch controller.
 */
public class FetchController {
    private FetchInputBoundary fetchInteractor;

    public FetchController(FetchInputBoundary fetchInteractor) {
        this.fetchInteractor = fetchInteractor;
    }

    /**
     * Execute interactor.
     */
    public void execute() {
        final FetchInputData fetchInputData = new FetchInputData();
        fetchInteractor.execute(fetchInputData);
    }
}
