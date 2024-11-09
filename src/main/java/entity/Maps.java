package entity;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class represent the map of the game.
 * Notice, the map generating process (randomly) should be done by map factory class and pass the grid to here in main.
 */

public class Maps {
    private ArrayList<ArrayList<Location>> grid;

    public Maps(ArrayList<ArrayList<Location>> grid) {
        this.grid = grid;

    }

    public ArrayList<ArrayList<Location>> getGrid() {
        return grid;
    }
}
