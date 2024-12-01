package interface_adapters.startallowcatepoint;

import usecases.startallowcate.AllowcateOutputBoundary;
import usecases.startallowcate.AllowcateOutputData;

/**
 * Allowcate presenter.
 */
public class AllowcatePresenter implements AllowcateOutputBoundary {
    private final AllowcateInterface view;

    public AllowcatePresenter(AllowcateInterface view) {
        this.view = view;
    }

    @Override
    public void preparesuccessview(AllowcateOutputData outputData) {
        view.updateUiAllowcate(outputData.getPoint(),
                outputData.getSocial(), outputData.getLuck(),
                outputData.getMobilization(), outputData.getThrift(), outputData.getGeneralship());
    }

    @Override
    public void preparefailureview(String failmessage) {
        view.failureAllowcate(failmessage);
    }
}
