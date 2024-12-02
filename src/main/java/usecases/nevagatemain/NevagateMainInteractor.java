package usecases.nevagatemain;

/**
 * Nevagate interactor.
 */
public class NevagateMainInteractor implements NevagateMainInputBoundary {
    private final NevagateMainDataAccessInterface dataaccess;
    private final NevagateMainOutputBoundary outputboundary;

    public NevagateMainInteractor(NevagateMainDataAccessInterface dataaccess,
                                  NevagateMainOutputBoundary outputboundary) {
        this.dataaccess = dataaccess;
        this.outputboundary = outputboundary;
    }

    @Override
    public void execute(NevagateMainInputdata inputdata) {
        outputboundary.prepareMainView();
    }
}
