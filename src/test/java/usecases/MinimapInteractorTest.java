package usecases;

import entities.*;
import org.junit.Test;
import usecases.gameminimap.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MinimapInteractorTest {

    @Test
    public void testMinimapInteractorSuccess() {
        ArrayList<String> environments = new ArrayList<>(Arrays.asList("Iceland", "Desert", "Forest", "City", "Plain"));
        MapFactory Mapfact =new MapFactory();
        ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> cores =
                Mapfact.getCores(100, 100, environments);
        ArrayList<ArrayList<Location>> grids = Mapfact.getGrids(100, 100, cores);
        Maps map = new Maps(grids);
        MinimapDataAccessInterface MockDAO = new MinimapDataAccessInterface() {
            @Override
            public PlayerLocation getPlayerLocation() {
                return new PlayerLocation(0,0);
            }

            @Override
            public Maps getMaps() {
                return map;
            }
        };
        MinimapOutputBoundary mockPresenter = new MinimapOutputBoundary() {
            @Override
            public void preparesuccessview(MinimapOutputData outputdata) {
                ArrayList<ArrayList<String>> minimap = new ArrayList<>();
                for(int x = -4; x <= 4; x++) {
                    ArrayList<String> row = new ArrayList<>();
                    for(int y = -4; y <= 4; y++) {
                        if (EntityConstants.MAPWIDTH >= x + 1 && EntityConstants.MAPHEIGHT >= y + 1 && x >= 0 && y >= 0) {
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
                System.out.println(minimap);
                assertEquals(minimap, outputdata.getMinimap());
            }

        };
        MinimapInteractor MinimapInteractor = new MinimapInteractor(MockDAO, mockPresenter);
        MinimapInteractor.execute(new MinimapInputData());
    }
}
