package entity;

/**
 * This is location form of Iceland.
 */
public class Iceland implements Location {
    private double foodresource;
    private double waterresource;
    private double firearmresource;
    private double nonfirearmresource;
    private double peopleresource;
    private final double temperature;
    private final double threat;

    public Iceland(int dist) {
        this.foodresource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterresource = getwaterresourceavailable(dist);
        this.firearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.nonfirearmresource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleresource = getpeopleresourceavailable(dist);
        this.temperature = gettemperature(dist);
        this.threat = getthreatlevel(dist);
    }

    @Override
    public Double gettemperature(int dist) {
        return Entityconstants.DEFAULTTEMP - (Entityconstants.MAXTEMPDIFF * (1 / (dist + 1)));
    }

    @Override
    public Double getpeopleresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR - (1 / (dist + 1));
    }

    @Override
    public Double getfoodresourceavailable(int dist) {
        return this.foodresource;
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
        return Entityconstants.STARTERRESOURCESCALAR - (Entityconstants.MAXTHREATDIFF / (dist + 1));
    }

}
