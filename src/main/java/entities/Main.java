package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> environments = new ArrayList<>(Arrays.asList("Iceland", "Desert", "Forest", "City", "Plain"));
        MapFactory Mapfact =new MapFactory();
        ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> cores =
                Mapfact.getCores(100, 100, environments);
        ArrayList<ArrayList<Location>> grids = Mapfact.getGrids(100, 100, cores);
        Mapfact.printGrids(grids);
    }
}
