package entity;

/**
 * This is location form of plain, more like default, which is not any of special biome (iceland, desert etc).
 */
public class Plain implements Location {
    private double foodResource;
    private double waterResource;
    private double fireArmResource;
    private double nonFirearmResource;
    private double peopleResource;
    private final double temperature;
    private final double threat;

    public Plain() {
        this.foodResource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterResource = Entityconstants.STARTERRESOURCESCALAR;
        this.fireArmResource = Entityconstants.STARTERRESOURCESCALAR;
        this.nonFirearmResource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleResource = Entityconstants.STARTERRESOURCESCALAR;
        this.temperature = Entityconstants.DEFAULTTEMP;
        this.threat = Entityconstants.DEFAULTTHREAT;
    }

    @Override
    public Double gettemperature(int dist) {
        return temperature;
    }

    @Override
    public Double getpeopleresourceavailable(int dist) {
        return this.peopleResource;
    }

    @Override
    public Double getfoodresourceavailable(int dist) {
        return this.foodResource;
    }

    @Override
    public Double getwaterresourceavailable(int dist) {
        return this.waterResource;
    }

    @Override
    public Double getfirearmresourceavailable(int dist) {
        return this.fireArmResource;
    }

    @Override
    public Double getnonfirearmresourceavailable(int dist) {
        return this.nonFirearmResource;
    }

    @Override
    public void decreaseresourceavailable() {
        this.foodResource = this.foodResource * Entityconstants.RESOUCEDECREASERATIO;
        this.waterResource = this.waterResource * Entityconstants.RESOUCEDECREASERATIO;
        this.fireArmResource = this.fireArmResource * Entityconstants.RESOUCEDECREASERATIO;
        this.nonFirearmResource = this.nonFirearmResource * Entityconstants.RESOUCEDECREASERATIO;
        this.peopleResource = this.peopleResource * Entityconstants.RESOUCEDECREASERATIO;
    }

    @Override
    public Double getthreatlevel(int dist) {
        return this.threat;
    }

}
