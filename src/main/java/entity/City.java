package entity;

/**
 * This is city subtype of location.
 */
public class City implements Location {
    private double foodresource;
    private double waterresource;
    private double firearmresource;
    private double nonfirearmresource;
    private double peopleresource;
    private double temperature;
    private double threat;

    public City(int dist) {
        this.foodresource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterresource = Entityconstants.STARTERRESOURCESCALAR;
        this.firearmresource = getfirearmresourceavailable(dist);
        this.nonfirearmresource = getnonfirearmresourceavailable(dist);
        this.peopleresource = getpeopleresourceavailable(dist);
        this.temperature = Entityconstants.STARTERRESOURCESCALAR;
        this.threat = getthreatlevel(dist);
    }

    @Override
    public Double gettemperature(int dist) {
        return this.temperature;
    }

    @Override
    public Double getpeopleresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getnonfirearmresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }
}
