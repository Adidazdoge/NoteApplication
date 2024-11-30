package interface_adapters.gameminimap;

import usecases.gameminimap.MinimapInputBoundary;
import usecases.gameminimap.MinimapInputData;

/**
 * Controller for minimap update ui usecase.
 */
public class MinimapController {
    private MinimapInputBoundary minimapInteractor;

    public MinimapController(MinimapInputBoundary minimapInteractor) {
        this.minimapInteractor = minimapInteractor;
    }

    /**
     * Execute by taking info from player side, turn to inputdata type, and sent to execute.
     */
    public void execute() {
        final MinimapInputData inputdata = new MinimapInputData();
        minimapInteractor.execute(inputdata);
    }

}
