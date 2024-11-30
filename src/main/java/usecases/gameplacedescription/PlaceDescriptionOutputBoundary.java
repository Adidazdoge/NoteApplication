package usecases.gameplacedescription;

/**
 * Output boundary of place description. success view will display the place description for the player, giving them
 * basic information of the place, for example how dangerous it is? cold or warm? etc
 */
public interface PlaceDescriptionOutputBoundary {

    /**
     * Prepare the view if the usecase is a success, which display the description generated by interactor.
     * @param data contain description required.
     */
    void preparesuccessview(PlaceDescriptionOutputData data);

    /**
     * Prepare fialure view, This shouldn't happen as long as player is inside the map grid, but player is always
     * inside the grid if move usecase was correct.
     * @param failmessage failmessage  ?
     */
    void preparefailureview(String failmessage);
}
