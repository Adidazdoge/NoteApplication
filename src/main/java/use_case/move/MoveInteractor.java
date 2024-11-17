package use_case.move;

import entity.Entityconstants;
import entity.PlayerLocation;

/**
 * Move use case interactor.
 */
public class MoveInteractor implements MoveInputBoundary {
    private final MoveDataAccessInterface moveDataAccessObject;
    private final MoveOutputBoundary moveOutputBoundary;

    public MoveInteractor(MoveDataAccessInterface moveDataAccessObject, MoveOutputBoundary moveOutputBoundary) {
        this.moveDataAccessObject = moveDataAccessObject;
        this.moveOutputBoundary = moveOutputBoundary;
    }

    @Override
    public void execute(MoveInputData moveInputData) {
        // Fetch necessary data
        final String direction = moveInputData.getDirection();
        final int speed = 1 + (moveDataAccessObject.getPlayerAttributes().getMobilization()
                / Entityconstants.MOBILIZATIONIMPACTSPEED);
        final PlayerLocation currentLocation = moveDataAccessObject.getPlayerLocation();
        final int x = currentLocation.getXcoordinate();
        final int y = currentLocation.getYcoordinate();
        final int mapWidth = moveDataAccessObject.getMaps().getGrid().size();
        final int mapHeight = moveDataAccessObject.getMaps().getGrid().get(1).size();

        // Variables for new coordinates
        int newX = x;
        int newY = y;

        // Determine new coordinates based on the direction
        switch (direction) {
            case Entityconstants.UP:
                newY = Math.max(0, y - speed);
                break;
            case Entityconstants.DOWN:
                newY = Math.min(mapHeight - 1, y + speed);
                break;
            case Entityconstants.LEFT:
                newX = Math.max(0, x - speed);
                break;
            case Entityconstants.RIGHT:
                newX = Math.min(mapWidth - 1, x + speed);
                break;
            default:
                moveOutputBoundary.prepareFailureView("Invalid direction provided: " + direction);
        }

        // Check if the move is valid
        if (isInvalidMove(direction, x, y, newX, newY, mapWidth, mapHeight)) {
            moveOutputBoundary.prepareFailureView(
                    "You can't move further in the " + direction + " direction; you're at the edge.");
        }
        else {
            moveDataAccessObject.updatePlayerLocation(newX, newY);
            final String successMessage = "You moved to position (" + newX + ", " + newY + ").";
            moveOutputBoundary.prepareSuccessView(new MoveOutputData(newX, newY, true, successMessage));
        }
    }

    /**
     * Helper method to check if a move is invalid.
     * @param direction direction heading.
     * @param mapHeight height of the map.
     * @param mapWidth width of the map.
     * @param newX Changed x.
     * @param newY Changed y.
     * @param xcoor x of player location currently.
     * @param ycoor y of player location currently.
     * @return Return if this is a valid move.
     */
    private boolean isInvalidMove(String direction, int xcoor, int ycoor,
                                  int newX, int newY, int mapWidth, int mapHeight) {
        boolean ans = false;
        if (direction.equals(Entityconstants.UP)) {
            ans = ycoor == 0 || newY == ycoor;
        }
        else if (direction.equals(Entityconstants.DOWN)) {
            ans = ycoor == mapHeight - 1 || newY == ycoor;
        }
        else if (direction.equals(Entityconstants.LEFT)) {
            ans = xcoor == 0 || newX == xcoor;
        }
        else if (direction.equals(Entityconstants.RIGHT)) {
            ans = xcoor == mapWidth - 1 || newX == xcoor;
        }
        return ans;
    }
}
