package entities;

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
        this.foodresource = EntityConstants.STARTERRESOURCESCALAR;
        this.waterresource = getsetwaterresourceavailable(dist);
        this.weaponresource = EntityConstants.STARTERRESOURCESCALAR;
        this.peopleresource = getsetpeopleresourceavailable(dist);
        this.temperature = getsettemperature(dist);
        this.threat = getsetthreatlevel(dist);
    }

    @Override
    public Double getsettemperature(int dist) {
        return EntityConstants.DEFAULTTEMP - (EntityConstants.MAXTEMPDIFF * (1 / (dist + 1)));
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
        return EntityConstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return EntityConstants.STARTERRESOURCESCALAR - (EntityConstants.MAXTHREATDIFF / (dist + 1));
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }

    @Override
    public String toString() {
        return EntityConstants.ICELAND;
    }

    @Override
    public String getDescription() {
        final String baseDescription =
                "The icy tundra stretches endlessly, a barren landscape of snow and frost. "
                        + "Jagged ice formations rise like silent sentinels, "
                        + "and the wind howls across the open plains.";

        // Add temperature-based phrasing
        final String temperatureDescription;
        if (this.temperature >= EntityConstants.ICELANDCOLD) {
            temperatureDescription =
                    "The cold is biting but manageable. Your group trudges carefully, keeping an eye out for shelter "
                            + "as frost begins to settle on your gear.";
        }
        else if (this.temperature <= EntityConstants.ICELANDEXTREMECOLD) {
            temperatureDescription =
                    "The cold is deadly, cutting through even the thickest clothing. Frostbite threatens your group, "
                            + "and every breath feels like shards of ice tearing through your lungs.";
        }
        else {
            temperatureDescription =
                    "The freezing air saps your strength as you march onward. "
                            + "The snow is deep, and every step feels heavier, "
                            + "but the group presses on, desperate to find refuge before the cold worsens.";
        }

        // Combine base and dynamic temperature descriptions
        return baseDescription + " " + temperatureDescription;
    }

}
