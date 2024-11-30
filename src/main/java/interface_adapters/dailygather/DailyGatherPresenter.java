package interface_adapters.dailygather;

import usecases.dailygather.GatherOutputBoundary;
import usecases.dailygather.GatherOutputData;

/**
 * Daily gather usecase presentor.
 */
public class DailyGatherPresenter implements GatherOutputBoundary {
    private DailyGatherInterface view;

    @Override
    public void prepareSuccessView(GatherOutputData outputdata) {
        view.updateUiGather(outputdata.getSuccessmessage(),
                outputdata.getUpdatedfood(), outputdata.getUpdatedwater(), outputdata.getUpdatedweapon());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureGather(errorMessage);
    }
}
