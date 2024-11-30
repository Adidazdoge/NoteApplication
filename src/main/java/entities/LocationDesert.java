package entities;

/**
 * This is location form of Desert.
 */
public class LocationDesert implements Location {
    private double foodresource;
    private double waterresource;
    private double weaponresource;
    private double peopleresource;
    private final double temperature;
    private final double threat;

    public LocationDesert(int dist) {
        this.foodresource = EntityConstants.STARTERRESOURCESCALAR;
        this.waterresource = getsetwaterresourceavailable(dist);
        this.weaponresource = EntityConstants.STARTERRESOURCESCALAR;
        this.peopleresource = getsetpeopleresourceavailable(dist);
        this.temperature = getsettemperature(dist);
        this.threat = EntityConstants.STARTERRESOURCESCALAR;
    }

    @Override
    public Double getsettemperature(int dist) {
        return EntityConstants.DEFAULTTEMP + (EntityConstants.MAXTEMPDIFF * (1 / (dist + 1)));
    }

    @Override
    public Double gettemperature() {
        return this.temperature;
    }

    @Override
    public Double getsetpeopleresourceavailable(int dist) {
        return EntityConstants.STARTERRESOURCESCALAR - (1 / (dist + 1));
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
        return EntityConstants.STARTERRESOURCESCALAR - (1 / (dist + 1));
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
        this.foodresource = this.foodresource * EntityConstants.RESOUCEDECREASERATIO;
        this.waterresource = this.waterresource * EntityConstants.RESOUCEDECREASERATIO;
        this.weaponresource = this.weaponresource * EntityConstants.RESOUCEDECREASERATIO;
    }

    @Override
    public void decreaserepeopleavailable() {
        this.peopleresource = this.peopleresource * EntityConstants.RESOUCEDECREASERATIO;
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
        return EntityConstants.DESERT;
    }

    @Override
    public String getDescription() {
        final String baseDescription =
                "The desert stretches endlessly, its golden dunes now marred by the occasional husk of a car "
                        + "or the skeletal remains of travelers who did not make it. The relentless sun beats down, "
                        + "offering no shelter from the horrors that might still wander here.";

        // Add temperature-based phrasing
        final String temperatureDescription;
        if (this.temperature <= EntityConstants.DESERTHOTDEGREE) {
            temperatureDescription =
                    "Your group finds some relief near the desert's edge. The air is warm but bearable, and "
                            + "a faint breeze carries a hint of moisture, suggesting you are not yet in the heart of "
                            + "this wasteland.";
        }
        else if (this.temperature >= EntityConstants.DESERTUNBAREABLEDEGREE) {
            temperatureDescription =
                    "Your group struggles under the merciless heat, the sun blazing down like an open furnace. "
                            + "The ground is too hot to touch, and every step feels like it could be your last. "
                            + "Supplies will not last long in this heat.";
        }
        else {
            temperatureDescription =
                    "The heat is punishing, but not unbearable yet. The deeper your group ventures into this "
                            + "wasteland, the more the sun saps your strength and the threat of "
                            + "dehydration looms ever larger.";
        }

        // Combine base and dynamic temperature descriptions
        return baseDescription + " " + temperatureDescription;
    }

}
