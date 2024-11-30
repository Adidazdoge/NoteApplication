package interface_adapters.fetchresource;

import usecases.fetchresource.FetchOutputBoundary;
import usecases.fetchresource.FetchOutputData;

/**
 * Fetch presenter.
 */
public class FetchPresenter implements FetchOutputBoundary {
    private FetchInterface view;

    public FetchPresenter(FetchInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessview(FetchOutputData outputdata) {
        view.updateUiResource(outputdata.getFood(), outputdata.getWater(),
                outputdata.getPeople(), outputdata.getWeapon());
    }
}
