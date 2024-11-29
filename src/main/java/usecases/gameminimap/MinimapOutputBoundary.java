package usecases.gameminimap;

/**
 * The output Boundary of minimap usecase, prepare successview.
 */
public interface MinimapOutputBoundary {

    /**
     * If the minimap usecase was a success, this should prepare the view on the minimap using the list of list of char.
     * @param outputdata list of list of char representing each location type.
     */
    void preparesuccessview(MinimapOutputData outputdata);
}
