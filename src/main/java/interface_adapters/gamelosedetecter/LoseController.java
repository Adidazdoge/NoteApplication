package interface_adapters.gamelosedetecter;

import usecases.gamelosedetecter.LoseInputBoundary;
import usecases.gamelosedetecter.LoseInputData;

public class LoseController {
    private LoseInputBoundary interactor;

    public LoseController(LoseInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final LoseInputData inputData = new LoseInputData();
        interactor.execute(inputData);
    }
}
