package entities;

import java.util.*;

/**
 * This class is responsible for creating the map with randomization.
 * Method returns location of core for resource calculation.
 * Method returns the grid for map data.
 */
public class MapFactory {

    /**
     * Method for generating different core for each environment in the format
     * [{City: [(100, 200), (50 100)]}, {iceland: [(50, 50)]}]
     * In this example, city have two core (100, 200) (50, 100), which have be used for calculating resources scalar.
     * @param xWidth Map size, the width
     * @param yLength Map size, the length
     * @param typeOfEnvironment list of different environments, iceland woods etc.
     * @return Return the description above in that format.
     */
    public ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> getCores(
            final int xWidth, final int yLength, final ArrayList<String> typeOfEnvironment) {
        final ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> environmentCores = new ArrayList<>();
        final Random random = new Random();
        for (final String environment : typeOfEnvironment) {
            final ArrayList<Map.Entry<Integer, Integer>> coreCoordinates = new ArrayList<>();
            final int numberOfCores = 1 + random.nextInt(5);
            for (int i = 0; i < numberOfCores; i++) {
                Map.Entry<Integer, Integer> newCore;
                boolean validCore;
                do {
                    final int x = random.nextInt(xWidth);
                    final int y = random.nextInt(yLength);
                    newCore = new AbstractMap.SimpleEntry<>(x, y);
                    validCore = true;
                    for (Map.Entry<Integer, Integer> existingCore : coreCoordinates) {
                        if (calculateDistance(existingCore, newCore) < EntityConstants.CORERANGE) {
                            validCore = false;
                            break;
                        }
                    }
                } while (!validCore);
                coreCoordinates.add(newCore);
            }
            final Map<String, ArrayList<Map.Entry<Integer, Integer>>> environmentMap = new HashMap<>();
            environmentMap.put(environment, coreCoordinates);
            environmentCores.add(environmentMap);
        }
        return environmentCores;
    }

    // This is a hlper method
    private double calculateDistance(Map.Entry<Integer, Integer> point1, Map.Entry<Integer, Integer> point2) {
        final int xDiff = point1.getKey() - point2.getKey();
        final int yDiff = point1.getValue() - point2.getValue();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * Construct the map/grid using the cores provided.
     * @param xWidth Map size width.
     * @param yLength Map size length.
     * @param cores Different cores given.
     * @return grid made of locations
     */

    public ArrayList<ArrayList<Location>> getGrids(
            final int xWidth, final int yLength, ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> cores
    ) {
        final ArrayList<ArrayList<Location>> grids = initializeGrid(xWidth, yLength);
        for (Map<String, ArrayList<Map.Entry<Integer, Integer>>> environmentCores : cores) {
            for (Map.Entry<String, ArrayList<Map.Entry<Integer, Integer>>> entry : environmentCores.entrySet()) {
                updateGridWithEnvironment(grids, entry, xWidth, yLength);
            }
        }
        return grids;
    }

    // Helper method to initialize the grid with default plain as dealut.
    private ArrayList<ArrayList<Location>> initializeGrid(final int xWidth, final int yLength) {
        final ArrayList<ArrayList<Location>> grids = new ArrayList<>();
        for (int x = 0; x < xWidth; x++) {
            final ArrayList<Location> partGrids = new ArrayList<>();
            for (int y = 0; y < yLength; y++) {
                partGrids.add(new LocationPlain());
            }
            grids.add(partGrids);
        }
        return grids;
    }

    // Helper method to update the grid based on the environment and its core coordinates
    private void updateGridWithEnvironment(
            final ArrayList<ArrayList<Location>> grids,
            final Map.Entry<String, ArrayList<Map.Entry<Integer, Integer>>> environmentEntry,
            final int xWidth,
            final int yLength
    ) {
        final String environmentType = environmentEntry.getKey();
        final ArrayList<Map.Entry<Integer, Integer>> coordinates = environmentEntry.getValue();
        final int radius = EntityConstants.BIOMERADIUS;
        for (Map.Entry<Integer, Integer> coordinate : coordinates) {
            final int centerX = coordinate.getKey();
            final int centerY = coordinate.getValue();
            iterateGridWithinRadius(grids, environmentType, centerX, centerY, radius, xWidth, yLength);
        }
    }

    // Helper method to iterate over grid cells within a radius and modify them
    private void iterateGridWithinRadius(
            final ArrayList<ArrayList<Location>> grids,
            final String environmentType,
            final int centerX,
            final int centerY,
            final int radius,
            final int xWidth,
            final int yLength
    ) {
        for (int x = Math.max(0, centerX - radius); x < Math.min(xWidth, centerX + radius); x++) {
            for (int y = Math.max(0, centerY - radius); y < Math.min(yLength, centerY + radius); y++) {
                final int distance = (int) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                if (distance <= radius) {
                    final Location newLocation = createLocationByTypeAndDistance(environmentType, distance);
                    grids.get(x).set(y, newLocation);
                }
            }
        }
    }

    // Helper method to create a Location
    private Location createLocationByTypeAndDistance(String environmentType, int distance) {
        switch (environmentType) {
            case EntityConstants.CITY:
                return new LocationCity(distance);
            case EntityConstants.FOREST:
                return new LocationForest(distance);
            case EntityConstants.PLAIN:
                return new LocationPlain();
            case EntityConstants.ICELAND:
                return new LocationIceland(distance);
            case EntityConstants.DESERT:
                return new LocationDesert(distance);
            default:
                return new LocationPlain();
        }
    }

    /**
     * Prints the grid to the console, using specific characters to represent each Location type.
     * P - Plain, H - Iceland, D - Desert, C - City, F - Forest
     * @param grids The grid made of locations.
     */
    public void printGrids(ArrayList<ArrayList<Location>> grids) {
        for (ArrayList<Location> row : grids) {
            for (Location loc : row) {
                // Determine the character representation based on the Location type
                if (loc instanceof LocationPlain) {
                    System.out.print("P ");
                }
                else if (loc instanceof LocationIceland) {
                    System.out.print("i ");
                }
                else if (loc instanceof LocationDesert) {
                    System.out.print("  ");
                }
                else if (loc instanceof LocationCity) {
                    System.out.print("W ");
                }
                else if (loc instanceof LocationForest) {
                    System.out.print("' ");
                }
                else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

}
