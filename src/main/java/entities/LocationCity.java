package entities;

/**
 * This is city subtype of location.
 */
public class LocationCity implements Location {
    private double foodresource;
    private double waterresource;
    private double weaponresource;
    private double peopleresource;
    private double temperature;
    private double threat;

    public LocationCity(int dist) {
        this.foodresource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterresource = Entityconstants.STARTERRESOURCESCALAR;
        this.weaponresource = getsetweaponresourceavailable(dist);
        this.peopleresource = getsetpeopleresourceavailable(dist);
        this.temperature = Entityconstants.STARTERRESOURCESCALAR;
        this.threat = getsetthreatlevel(dist);
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
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getpeopleresourceavailable() {
        return this.peopleresource;
    }

    @Override
    public Double getsetfoodresourceavailable(int dist) {
        return this.foodresource;
    }

    @Override
    public Double getfoodresourceavailable() {
        return this.foodresource;
    }

    @Override
    public Double getsetwaterresourceavailable(int dist) {
        return this.waterresource;
    }

    @Override
    public Double getwaterresourceavailable() {
        return this.waterresource;
    }

    @Override
    public Double getsetweaponresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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

    }

    @Override
    public void decreaserepeopleavailable() {
        this.peopleresource = this.peopleresource * Entityconstants.RESOUCEDECREASERATIO;
    }

    @Override
    public Double getsetthreatlevel(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }
}
