package interface_adapters.gameminimap;

import java.util.ArrayList;

import usecases.gameminimap.MinimapOutputBoundary;
import usecases.gameminimap.MinimapOutputData;

/**
 * Minimap presentor.
 */
public class MinimapPresenter implements MinimapOutputBoundary {
    private MinimapInterface view;

    public MinimapPresenter(MinimapInterface view) {
        this.view = view;
    }

    @Override
    public void preparesuccessview(MinimapOutputData outputdata) {
        final ArrayList<ArrayList<String>> grid = outputdata.getMinimap();
        view.updateUiMinimap(grid);
    }
}
