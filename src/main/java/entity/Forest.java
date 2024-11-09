package entity;

/**
 * This is woods subtype of location.
 */
public class Forest implements Location {
    private double foodresource;
    private double waterresource;
    private double firearmresource;
    private double nonfirearmresource;
    private double peopleresource;
    private double temperature;
    private double threat;

    public Forest(int dist) {
        this.foodresource = getfoodresourceavailable(dist);
        this.waterresource = getwaterresourceavailable(dist);
        this.firearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.nonfirearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleresource = Entityconstants.STARTERRESOURCESCALAR;
        this.temperature = Entityconstants.STARTERRESOURCESCALAR;
        this.threat = Entityconstants.STARTERRESOURCESCALAR;
    }

    @Override
    public Double gettemperature(int dist) {
        return this.temperature;
    }

    @Override
    public Double getpeopleresourceavailable(int dist) {
        return this.peopleresource;
    }

    @Override
    public Double getfoodresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getwaterresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
