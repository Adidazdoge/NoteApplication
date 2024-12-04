package usecases.gameplacedescription;

import entities.Location;

/**
 * Place Description interactor, based on location information, generate the description for the player,
 * which is for the view.
 */
public class PlaceDescriptionInteractor implements PlaceDescriptionInputBoundary {
    private PlaceDescriptionDataAccessInterface dataAccess;
    private PlaceDescriptionOutputBoundary outputBoundary;

    public PlaceDescriptionInteractor(PlaceDescriptionDataAccessInterface dataaccess,
                                      PlaceDescriptionOutputBoundary outputboundary) {
        this.dataAccess = dataaccess;
        this.outputBoundary = outputboundary;
    }

    @Override
    public void execute(PlaceDescriptionInputData Inputdata) {
        final Location location = dataAccess.getLocation();
        final String description = location.getDescription();
        final PlaceDescriptionOutputData outputdata = new PlaceDescriptionOutputData(description);
        outputBoundary.preparesuccessview(outputdata);

    }
}
