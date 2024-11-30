package interface_adapters.dailymove;

import usecases.dailymove.MoveInputBoundary;
import usecases.dailymove.MoveInputData;

/**
 * Move controller, used to make player input into inputdata type for move usecase.
 */
public class DailyMoveController {
    private MoveInputBoundary moveInteractor;

    public DailyMoveController(MoveInputBoundary moveInteractor) {
        this.moveInteractor = moveInteractor;
    }

    /**
     * Serve as execute the interactor by converting player input raw data to inputdata type.
     * @param direction direction player inputed.
     */
    public void execute(String direction) {
        final MoveInputData inputdata = new MoveInputData(direction);
        moveInteractor.execute(inputdata);
    }
}
