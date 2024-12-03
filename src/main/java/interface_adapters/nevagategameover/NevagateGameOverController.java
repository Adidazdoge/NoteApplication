package interface_adapters.nevagategameover;

import usecases.nevagateGameover.NevagateGameOverInputBoundary;
import usecases.nevagateGameover.NevagateGameOverInputdata;

/**
 * Controller for navigate.
 */
public class NevagateGameOverController {
    private final NevagateGameOverInputBoundary interactor;

    public NevagateGameOverController(NevagateGameOverInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute this.
     */
    public void execute() {
        final NevagateGameOverInputdata inputdata = new NevagateGameOverInputdata();
        interactor.execute(inputdata);
    }
}
