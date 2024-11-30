package interface_adapters.dailymove;

import usecases.dailymove.MoveOutputBoundary;
import usecases.dailymove.MoveOutputData;

/**
 * Presentor of move.
 */
public class DailyMovePresenter implements MoveOutputBoundary {
    private DailyMoveInterface view;

    public DailyMovePresenter(DailyMoveInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(MoveOutputData outputData) {
        view.updateUiMove(outputData.getmessage());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.updateUiMove(errorMessage);
    }
}
