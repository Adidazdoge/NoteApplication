package usecases.gameplacedescription;

/**
 * Output data for this usecase, which is just description.
 */
public class PlaceDescriptionOutputData {
    private String description;

    public PlaceDescriptionOutputData(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
