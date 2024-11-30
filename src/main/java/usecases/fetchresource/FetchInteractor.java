package usecases.fetchresource;

/**
 * Data access interactor for fetch, this is used to provide real time and updated resource player had to display.
 * Will get called after any usecase that changed player's inventory.
 */
public class FetchInteractor implements FetchInputBoundary {
    private FetchDataAccessInterface dataaccess;
    private FetchOutputBoundary outputBoundary;

    public FetchInteractor(FetchDataAccessInterface dataaccess, FetchOutputBoundary outputBoundary) {
        this.dataaccess = dataaccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(FetchInputData inputData) {
        final FetchOutputData outputdata = new FetchOutputData(dataaccess.getInventory().getFood(),
                dataaccess.getInventory().getWater(), dataaccess.getInventory().getPeople(),
                dataaccess.getInventory().getWeapon());
        outputBoundary.prepareSuccessview(outputdata);
    }
}
