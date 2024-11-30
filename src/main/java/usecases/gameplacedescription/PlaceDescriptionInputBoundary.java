package usecases.gameplacedescription;

/**
 * Input boundary of place description, which a interactor should have execute method, providing input data.
 */
public interface PlaceDescriptionInputBoundary {
    /**
     * Execute by retuning the description of the location.
     * @param inputdata not really needed as this is automatic move.
     */
    void execute(PlaceDescriptionInputData inputdata);
}
