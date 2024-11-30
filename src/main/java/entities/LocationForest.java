package entities;

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
        this.weaponresource = EntityConstants.STARTERRESOURCESCALAR;
        this.peopleresource = EntityConstants.STARTERRESOURCESCALAR;
        this.temperature = EntityConstants.STARTERRESOURCESCALAR;
        this.threat = EntityConstants.STARTERRESOURCESCALAR;
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
        return EntityConstants.STARTERRESOURCESCALAR + (1 / (dist + 1));
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
        return this.threat;
    }

    @Override
    public Double getthreatlevel() {
        return this.threat;
    }

    @Override
    public String toString() {
        return EntityConstants.FOREST;
    }

    @Override
    public String getDescription() {
        final String baseDescription =
                "The forest feels alive yet foreboding, its towering trees "
                        + "stretching high above and casting long shadows. "
                        + "The air is rich with the scent of damp earth, and "
                        + "the undergrowth grows thick as your group ventures deeper.";

        // Determine resource-based phrasing
        final String resourceDescription;
        if (this.foodresource <= EntityConstants.FORESTRICH
                && this.waterresource <= EntityConstants.FORESTEXTREMERICH) {
            resourceDescription =
                    "At the forest's edge, food and water are scarce. The sparse undergrowth offers little sustenance, "
                            + "and your group must decide carefully whether to move deeper in search of resources.";
        }
        else if (this.foodresource >= EntityConstants.FORESTEXTREMERICH
                && this.waterresource >= EntityConstants.FORESTEXTREMERICH) {
            resourceDescription =
                    "Deep within the heart of the forest, resources are plentiful. "
                            + "Your group finds wild fruits, clear streams, "
                            + "and signs of abundant wildlife. Yet the dense trees "
                            + "and eerie quiet suggest danger might not be far away.";
        }
        else {
            resourceDescription =
                    "The forest offers a mix of hope and caution. While food and water are becoming easier to find, "
                            + "the growing density of the undergrowth hints at "
                            + "the challenges and risks that lie ahead.";
        }

        // Combine base and dynamic resource descriptions
        return baseDescription + " " + resourceDescription;
    }

}
