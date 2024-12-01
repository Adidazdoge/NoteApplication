package entities;

/**
 * The class represent the horde by it's magnitude and duration. specific calculation involves other class info are for
 * Use case interact.
 */
public class Horde {
    private int magnitude;
    // people and firepower requirement.
    private double duration;
    // food and water requirement.

    public Horde() {
        this.magnitude = EntityConstants.STARTERHORDEMAGNITUDE;
        this.duration = EntityConstants.STARTERHORDEDURATION;
    }

    public Horde(int magnitude, double duration) {
        this.magnitude = magnitude;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    /**
     * Get the description for the horde, based on threatlevel and temperature of which player ended up on.
     * @param threatLevel threat level, normal at 1, peak at 2
     * @param temperature temperature, normal at 25, max diff at 50
     * @return the description.
     */
    public String getDescription(double threatLevel, double temperature) {
        // Adjust magnitude based on threat level
        this.magnitude = (int) (magnitude * threatLevel);

        // Adjust duration based on how far the temperature is from the ideal (25°C)
        final double tempDifference = Math.abs(temperature - EntityConstants.DEFAULTTEMP);
        this.duration = duration * (1 - ((EntityConstants.TEMPIMPACTHORDE * tempDifference)
                / EntityConstants.MAXTEMPDIFF));

        // Generate descriptions
        final String magnitudeDescription;
        if (this.magnitude < EntityConstants.HORDEMAGNITUDELARGE) {
            magnitudeDescription = "around " + EntityConstants.HORDEMAGNITUDELARGE
                    + " zombies, scattered into smaller groups.";
        }
        else if (this.magnitude < EntityConstants.HORDEMAGNITUDEEXTREME) {
            magnitudeDescription = "approximately " + EntityConstants.HORDEMAGNITUDEEXTREME
                    + " zombies, moving with relentless determination.";
        }
        else {
            magnitudeDescription = "an overwhelming mass of about " + this.magnitude
                    + " zombies, consuming everything in their path.";
        }

        final String durationHint;
        if (temperature < EntityConstants.ICELANDEXTREMECOLD) {
            durationHint = "The bitter cold seems to sap their endurance, they won't last long out here.";
        }
        else if (temperature < EntityConstants.ICELANDCOLD) {
            durationHint = "The cool air keeps them moving steadily, but they may tire eventually.";
        }
        else if (temperature > EntityConstants.DESERTUNBAREABLEDEGREE) {
            durationHint = "The searing heat is taking its toll, they wont hold out for long under these conditions.";
        }
        else if (temperature > EntityConstants.DESERTHOTDEGREE) {
            durationHint = "The heat made them active, but not for too long they'll dehydrate.";
        }
        else {
            durationHint = "The perfect temperature not only make your group comfortable,"
                    + " but also made the horde lasting longer.";
        }

        final String durationDescription;
        if (this.duration >= EntityConstants.HORDEDURATIONLONG) {
            durationDescription = "It lingers for days, taking advantage of the weather.";
        }
        else if (this.duration >= EntityConstants.HORDEDURATIONSHORT) {
            durationDescription = "It stays for a few days, but conditions will soon force it to dissipate.";
        }
        else {
            durationDescription = "It is fleeting, scattered quickly by the harsh environment.";
        }

        // Combine descriptions
        return "The horde consists of " + magnitudeDescription + " " + durationHint + " " + durationDescription;
    }

}
