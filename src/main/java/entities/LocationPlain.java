package entities;

/**
 * This is location form of plain, more like default, which is not any of special biome (iceland, desert etc).
 */
public class LocationPlain implements Location {
    private double foodResource;
    private double waterResource;
    private double weaponResource;
    private double peopleResource;
    private final double temperature;
    private final double threat;

    public LocationPlain() {
        this.foodResource = EntityConstants.STARTERRESOURCESCALAR;
        this.waterResource = EntityConstants.STARTERRESOURCESCALAR;
        this.weaponResource = EntityConstants.STARTERRESOURCESCALAR;
        this.peopleResource = EntityConstants.STARTERRESOURCESCALAR;
        this.temperature = EntityConstants.DEFAULTTEMP;
        this.threat = EntityConstants.DEFAULTTHREAT;
    }

    @Override
    public Double getsettemperature(int dist) {
        return temperature;
    }

    @Override
    public Double gettemperature() {
        return temperature;
    }

    @Override
    public Double getsetpeopleresourceavailable(int dist) {
        return this.peopleResource;
    }

    @Override
    public Double getpeopleresourceavailable() {
        return this.peopleResource;
    }

    @Override
    public Double getsetfoodresourceavailable(int dist) {
        return this.foodResource;
    }

    @Override
    public Double getfoodresourceavailable() {
        return this.foodResource;
    }

    @Override
    public Double getsetwaterresourceavailable(int dist) {
        return this.waterResource;
    }

    @Override
    public Double getwaterresourceavailable() {
        return this.waterResource;
    }

    @Override
    public Double getsetweaponresourceavailable(int dist) {
        return this.weaponResource;
    }

    @Override
    public Double getweaponresourceavailable() {
        return this.weaponResource;
    }

    @Override
    public void decreaseresourceavailable() {
        this.foodResource = this.foodResource * EntityConstants.RESOUCEDECREASERATIO;
        this.waterResource = this.waterResource * EntityConstants.RESOUCEDECREASERATIO;
        this.weaponResource = this.weaponResource * EntityConstants.RESOUCEDECREASERATIO;
    }

    @Override
    public void decreaserepeopleavailable() {
        this.peopleResource = this.peopleResource * EntityConstants.RESOUCEDECREASERATIO;
    }

    @Override
    public Double getsetthreatlevel(int dist) {
        return this.threat;
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }

    @Override
    public String toString() {
        return EntityConstants.PLAIN;
    }

    @Override
    public String getDescription() {
        return "The plains stretch endlessly in every direction, a sea of swaying grass under a pale, open sky. "
                + "The emptiness is both calming and unsettling, offering little cover should danger arise.";
    }
}
