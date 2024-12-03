package usecases.nevagateGameover;

/**
 * Interactor, only for navigating to the desired page.
 */
public class NevagateGameOverInteractor implements NevagateGameOverInputBoundary {
    private NevagateGameOverDataAccessInterface dataaccess;
    private NevagateGameOverOutputBoundary outputBoundary;

    public NevagateGameOverInteractor(NevagateGameOverDataAccessInterface dataaccess,
                                      NevagateGameOverOutputBoundary outputBoundary) {
        this.dataaccess = dataaccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(NevagateGameOverInputdata inputdata) {
        final NevagateGameOverOutputdata outputdata = new NevagateGameOverOutputdata();
        outputBoundary.prepareGameOver(outputdata);
    }
}
