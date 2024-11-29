package usecases.gameminimap;

import java.util.ArrayList;

/**
 * Output data type of minimap, this is list of list of single letter string representation of location.
 */
public class MinimapOutputData {
    private ArrayList<ArrayList<String>> minimap;

    public MinimapOutputData(ArrayList<ArrayList<String>> minimap) {
        this.minimap = minimap;
    }

    public ArrayList<ArrayList<String>> getMinimap() {
        return minimap;
    }
}
