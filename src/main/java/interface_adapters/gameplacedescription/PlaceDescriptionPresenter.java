package interface_adapters.gameplacedescription;

import usecases.gameplacedescription.PlaceDescriptionOutputBoundary;
import usecases.gameplacedescription.PlaceDescriptionOutputData;

/**
 * Presenter of place description.
 */
public class PlaceDescriptionPresenter implements PlaceDescriptionOutputBoundary {
    private PlaceDescriptionInterface view;

    @Override
    public void preparesuccessview(PlaceDescriptionOutputData data) {
        final String description = data.getDescription();
        view.updateUiPlaceDescription(description);
    }

    @Override
    public void preparefailureview(String failmessage) {
        view.failurePlaceDescription(failmessage);
    }
}
