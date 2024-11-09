package entity;

/**
 * This is location form of plain, more like default, which is not any of special biome (iceland, desert etc).
 */
public class Plain implements Location {
    private double foodresource;
    private double waterresource;
    private double firearmresource;
    private double nonfirearmresource;
    private double peopleresource;
    private final double temperature;
    private final double threat;

    public Plain() {
        this.foodresource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterresource = Entityconstants.STARTERRESOURCESCALAR;
        this.firearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.nonfirearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleresource = Entityconstants.STARTERRESOURCESCALAR;
        this.temperature = Entityconstants.DEFAULTTEMP;
        this.threat = Entityconstants.DEFAULTTHREAT;
    }

    @Override
    public Double gettemperature(int dist) {
        return temperature;
    }

    @Override
    public Double getpeopleresourceavailable(int dist) {
        return this.peopleresource;
    }

    @Override
    public Double getfoodresourceavailable(int dist) {
        return this.foodresource;
    }

    @Override
    public Double getwaterresourceavailable(int dist) {
        return this.waterresource;
    }

    @Override
    public Double getfirearmresourceavailable(int dist) {
        return this.firearmresource;
    }

    @Override
    public Double getnonfirearmresourceavailable(int dist) {
        return this.nonfirearmresource;
    }

    @Override
    public void decreaseresourceavailable() {
        this.foodresource = this.foodresource * Entityconstants.RESOUCEDECREASERATIO;
        this.waterresource = this.waterresource * Entityconstants.RESOUCEDECREASERATIO;
        this.firearmresource = this.firearmresource * Entityconstants.RESOUCEDECREASERATIO;
        this.nonfirearmresource = this.nonfirearmresource * Entityconstants.RESOUCEDECREASERATIO;
        this.peopleresource = this.peopleresource * Entityconstants.RESOUCEDECREASERATIO;
    }

    @Override
    public Double getthreatlevel(int dist) {
        return this.threat;
    }

}
