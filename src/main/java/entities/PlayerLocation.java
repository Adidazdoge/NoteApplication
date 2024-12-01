package entities;

/**
 * Class keep track of player location.
 */
public class PlayerLocation {
    private int xcoordinate;
    private int ycoordinate;

    public PlayerLocation() {
        this.xcoordinate = EntityConstants.SPAWNXCOOR;
        this.ycoordinate = EntityConstants.SPAWNYCOOR;
    }

    public PlayerLocation(int xcoordinate, int ycoordinate) {
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
    }

    public int getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(int xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public int getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(int ycoordinate) {
        this.ycoordinate = ycoordinate;
    }
}
