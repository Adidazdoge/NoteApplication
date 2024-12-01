package interface_adapters.gameplacedescription;

/**
 * Place description interface.
 */
public interface PlaceDescriptionInterface {

    /**
     * If successful usecase, display the place description.
     * @param placeDescription place description.
     */
    void updateUiPlaceDescription(String placeDescription);

    /**
     * If not successful, explain why location description generate was failed.
     * @param failmessage fail reasoning.
     */
    void failurePlaceDescription(String failmessage);
}
