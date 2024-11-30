package usecases.gameminimap;

/**
 * Providing inputdata, the interactor takes it and execute, though we don't need input from
 * player's side as it is automatic move.
 */
public interface MinimapInputBoundary {

    /**
     * Execute the interactor, providing inputdata.
     * @param inputdata inputdata type, we don't really need that.
     */
    void execute(MinimapInputData inputdata);
}
