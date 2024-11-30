package entities;

/**
 * Class responsible for storing attribution information and updates.
 * Note, the class shouldn't be called after player formally "started" the game as attributes are fixed.
 * Points: how many points you have to allocate. If difficulty extension introduced, change the starterattributepoint.
 * Charisma: Increase the chance and number of people joined after each broadcast.
 * Luck: Increase probability of positive events, Decrease probability of negative events.
 * Mobilization: Better mobilization ability allow group to move faster. num of action per day increase accordingly.
 * Thrift: Ability of better allocating resources, decrease food/water consumption.
 * Generalship: Increase firepower by changing/increase proportion of how it's calculated.
 * Could be more for modification added later.
 */

public class PlayerAttributes {
    private int points;
    // Control the amount of people involvement per day
    private int social;
    // Control the possibility of the positive effects
    private int luck;
    // Control the moving scale and moving times
    private int mobilization;
    // Control the resource consumption per individual per day
    private int thrift;
    // Control the level of firepower
    private int generalship;

    public PlayerAttributes() {
        this.points = EntityConstants.STARTERATRIBUTEPOINT;
        this.social = 0;
        this.luck = 0;
        this.mobilization = 0;
        this.thrift = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getMobilization() {
        return mobilization;
    }

    public void setMobilization(int mobilization) {
        this.mobilization = mobilization;
    }

    public int getThrift() {
        return thrift;
    }

    public void setThrift(int thrift) {
        this.thrift = thrift;
    }

    public int getGeneralship() {
        return generalship;
    }

    public void setGeneralship(int generalship) {
        this.generalship = generalship;
    }
}
