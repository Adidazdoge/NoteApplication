package entity;

/**
 * Class responsible for storing attribution information and updates.
 * Note, the class shouldn't be called after player formally "started" the game as attributes are fixed.
 * Points: how many points you have to allowcate. If difficulty extention introduced, change the starterattributepoint.
 * Charismma: Increase the chance and number of people joined after each broadcast.
 * Luck: Increase probability of positive events, Decrease probability of negative events.
 * Mobilization: Better mobilizartion ability allow group to move faster. num of action perday increase accordingly.
 * Thrift: Ability of better allowcating resources, decrease food/water consumption.
 * Generalship: Increase firepower by changing/increase perportion of how it's calculated.
 * Could be more for modification added later.
 */

public class PlayerAttributes {
    private int points;
    private int charisma;
    private int luck;
    private int mobilization;
    private int thrift;
    private int generalship;

    public PlayerAttributes() {
        this.points = Entityconstants.STARTERATRIBUTEPOINT;
        this.charisma = 0;
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

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
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
