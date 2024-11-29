package usecases.gameminimap;

import java.util.ArrayList;

import entities.EntityConstants;
import entities.Location;

/**
 * Minimap usecase interactor, given nothing from player's side, only data of player's location, and map.
 * Return the list of list of char used for mini map view prepare.
 */
public class MinimapInteractor implements MinimapInputBoundary {
    private MinimapDataAccessInterface dataaccess;
    private MinimapOutputBoundary outputBoundary;

    public MinimapInteractor(MinimapDataAccessInterface dataaccess, MinimapOutputBoundary outputBoundary) {
        this.dataaccess = dataaccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(MinimapInputData inputdata) {
        final ArrayList<ArrayList<String>> minimap = new ArrayList();
        final ArrayList<ArrayList<Location>> grids = dataaccess.getMaps().getGrid();
        final int xcoor = dataaccess.getPlayerLocation().getXcoordinate();
        final int minxcoor = xcoor - EntityConstants.MINIMAPRADIUS;
        final int maxxcoor = xcoor + EntityConstants.MINIMAPRADIUS;
        final int ycoor = dataaccess.getPlayerLocation().getYcoordinate();
        final int minycoor = ycoor - EntityConstants.MINIMAPRADIUS;
        final int maxycoor = ycoor + EntityConstants.MINIMAPRADIUS;
        for (int y = minycoor; y <= maxycoor; y++) {
            final ArrayList<String> row = new ArrayList();
            for (int x = minxcoor; x <= maxxcoor; x++) {
                if (EntityConstants.MAPWIDTH >= x + 1 && EntityConstants.MAPHEIGHT <= y + 1) {
                    row.add(grids.get(y).get(x).toString().substring(0, 1));
                    // get the first letter as representative of map grid.
                }
                else {
                    row.add(" ");
                    // empty representing outside of Map
                }
            }
            minimap.add(row);
        }
        final MinimapOutputData outputData = new MinimapOutputData(minimap);
        outputBoundary.preparesuccessview(outputData);
    }
}
