package usecases.gameplacedescription;

/**
 * Place Description interactor, based on location information, generate the description for the player,
 * which is for the view.
 */
public class PlaceDescriptionInteractor implements PlaceDescriptionInputBoundary {
    private PlaceDescriptionDataAccessInterface dataAccess;
    private PlaceDescriptionOutputBoundary outputBoundary;

    public PlaceDescriptionInteractor(PlaceDescriptionDataAccessInterface dataAccess,
                                      PlaceDescriptionOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(PlaceDescriptionInputData inputdata) {
        final String description = dataAccess.getLocation().getDescription();
        final PlaceDescriptionOutputData outputdata = new PlaceDescriptionOutputData(description);
        outputBoundary.preparesuccessview(outputdata);

    }
}
