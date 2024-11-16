package entity;

/**
 * This is woods subtype of location.
 */
public class LocationForest implements Location {
    private double foodresource;
    private double waterresource;
    private double weaponresource;
    private double peopleresource;
    private double temperature;
    private double threat;

    public LocationForest(int dist) {
        this.foodresource = getsetfoodresourceavailable(dist);
        this.waterresource = getsetwaterresourceavailable(dist);
        this.weaponresource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleresource = Entityconstants.STARTERRESOURCESCALAR;
        this.temperature = Entityconstants.STARTERRESOURCESCALAR;
        this.threat = Entityconstants.STARTERRESOURCESCALAR;
    }

    @Override
    public Double getsettemperature(int dist) {
        return this.temperature;
    }

    @Override
    public Double gettemperature() {
        return this.temperature;
    }

    @Override
    public Double getsetpeopleresourceavailable(int dist) {
        return this.peopleresource;
    }

    @Override
    public Double getpeopleresourceavailable() {
        return this.peopleresource;
    }

    @Override
    public Double getsetfoodresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getfoodresourceavailable() {
        return this.foodresource;
    }

    @Override
    public Double getsetwaterresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getwaterresourceavailable() {
        return this.waterresource;
    }

    @Override
    public Double getsetweaponresourceavailable(int dist) {
        return this.weaponresource;
    }

    @Override
    public Double getweaponresourceavailable() {
        return this.weaponresource;
    }

    @Override
    public void decreaseresourceavailable() {
        this.foodresource = this.foodresource * Entityconstants.RESOUCEDECREASERATIO;
        this.waterresource = this.waterresource * Entityconstants.RESOUCEDECREASERATIO;
        this.weaponresource = this.weaponresource * Entityconstants.RESOUCEDECREASERATIO;
        this.peopleresource = this.peopleresource * Entityconstants.RESOUCEDECREASERATIO;
    }

    @Override
    public Double getsetthreatlevel(int dist) {
        return this.threat;
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }
}
