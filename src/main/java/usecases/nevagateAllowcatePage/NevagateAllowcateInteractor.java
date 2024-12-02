package usecases.nevagateAllowcatePage;

/**
 * Interactor for nevagate, cehck if player is able to go to next page.
 */
public class NevagateAllowcateInteractor implements NevagateAllowcateInputBoundary {
    private final NevagateAllowcateDataAccessInterface dataAccess;
    private final NevagateAllowcateOutputBoundary outputBoundary;

    public NevagateAllowcateInteractor(NevagateAllowcateDataAccessInterface dataAccess,
                                       NevagateAllowcateOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(NevagateAllowcateInputdata inputdata) {
        // player should be able to go as long as they clicked the new game, so nothing is stopping them.
        outputBoundary.nevagateAllowcatePage();
        // go to allowcate page.
    }
}
