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
        this.foodresource = EntityConstants.STARTERRESOURCESCALAR;
        this.waterresource = EntityConstants.STARTERRESOURCESCALAR;
        this.weaponresource = getsetweaponresourceavailable(dist);
        this.peopleresource = getsetpeopleresourceavailable(dist);
        this.temperature = EntityConstants.STARTERRESOURCESCALAR;
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
        return EntityConstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return EntityConstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return EntityConstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }

    @Override
    public String toString() {
        return EntityConstants.CITY;
    }

    @Override
    public String getDescription() {
        final String baseDescription =
                "The city is a graveyard of skyscrapers, their windows shattered and facades crumbling. "
                        + "The streets are littered with rusting cars and decaying bodies, "
                        + "and the distant sound of groaning "
                        + "echoes down the alleyways.";

        // Add threat-based phrasing
        final String threatDescription;
        if (this.threat <= EntityConstants.CITYDANGER) {
            threatDescription =
                    "For now, the streets seem calm. Shadows shift in the distance, but it is quiet enough to risk "
                            + "searching for supplies. Your group moves cautiously, always watching for danger.";
        }
        else if (this.threat >= EntityConstants.CITYEXTREMEDANGER) {
            threatDescription =
                    "The city is swarming with the infected. Groans and shuffling footsteps echo from every direction, "
                            + "and the danger is impossible to ignore. "
                            + "Every turn feels like an ambush waiting to happen, and your "
                            + "group knows it must escape quickly or risk being overwhelmed.";
        }
        else {
            threatDescription =
                    "The city feels tense and unpredictable. While not overrun, signs of the infected are everywhere. "
                            + "Your group must tread carefully, "
                            + "as any sound or movement could draw unwanted attention.";
        }

        // Combine base and dynamic threat descriptions
        return baseDescription + " " + threatDescription;
    }

}
