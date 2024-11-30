package interface_adapters.dailymove;

import usecases.dailymove.MoveOutputBoundary;
import usecases.dailymove.MoveOutputData;

/**
 * Presentor of move.
 */
public class DailyMovePresentor implements MoveOutputBoundary {
    private DailyMoveInterface view;

    public DailyMovePresentor(DailyMoveInterface view) {
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
