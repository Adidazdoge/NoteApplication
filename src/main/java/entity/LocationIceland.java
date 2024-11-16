package entity;

/**
 * This is location form of Iceland.
 */
public class LocationIceland implements Location {
    private double foodresource;
    private double waterresource;
    private double weaponresource;
    private double peopleresource;
    private final double temperature;
    private final double threat;

    public LocationIceland(int dist) {
        this.foodresource = Entityconstants.STARTERRESOURCESCALAR;
        this.waterresource = getsetwaterresourceavailable(dist);
        this.weaponresource = Entityconstants.STARTERRESOURCESCALAR;
        this.peopleresource = getsetpeopleresourceavailable(dist);
        this.temperature = getsettemperature(dist);
        this.threat = getsetthreatlevel(dist);
    }

    @Override
    public Double getsettemperature(int dist) {
        return Entityconstants.DEFAULTTEMP - (Entityconstants.MAXTEMPDIFF * (1 / (dist + 1)));
    }

    @Override
    public Double gettemperature() {
        return this.temperature;
    }

    @Override
    public Double getsetpeopleresourceavailable(int dist) {
        return Entityconstants.STARTERRESOURCESCALAR - (1 / (dist + 1));
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
        return Entityconstants.STARTERRESOURCESCALAR - (Entityconstants.MAXTHREATDIFF / (dist + 1));
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }

}
