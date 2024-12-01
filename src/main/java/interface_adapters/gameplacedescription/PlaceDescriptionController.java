package interface_adapters.gameplacedescription;

import usecases.gameplacedescription.PlaceDescriptionInputBoundary;
import usecases.gameplacedescription.PlaceDescriptionInputData;

/**
 * Place description usecase controller.
 */
public class PlaceDescriptionController {
    private PlaceDescriptionInputBoundary interactor;

    public PlaceDescriptionController(PlaceDescriptionInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Automatic move, no player input needed.
     */
    public void execute() {
        final PlaceDescriptionInputData inputdata = new PlaceDescriptionInputData();
        interactor.execute(inputdata);
    }
}
