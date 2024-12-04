package usecases.navigateMain;

/**
 * Nevagate interactor.
 */
public class NavigateMainInteractor implements NavigateMainInputBoundary {
    private final NavigateMainDataAccessInterface dataAccess;
    private final NevagateMainOutputBoundary outputBoundary;

    public NavigateMainInteractor(NavigateMainDataAccessInterface dataaccess,
                                  NevagateMainOutputBoundary outputboundary) {
        this.dataAccess = dataaccess;
        this.outputBoundary = outputboundary;
    }

    @Override
    public void execute(NavigateMainInputData inputdata) {
        outputBoundary.prepareMainView();
    }
}
