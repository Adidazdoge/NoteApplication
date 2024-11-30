package interface_adapters.gameminimap;

import java.util.ArrayList;

/**
 * Interface for minimap, updateminimap ui.
 */
public interface MinimapInterface {

    /**
     * Updata Minimap with nested list of single letter.
     * @param grid representation of each loc form together to be minimap.
     */
    void updateUiMinimap(ArrayList<ArrayList<String>> grid);
}
