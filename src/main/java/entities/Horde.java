package entities;

/**
 * The class represent the horde by it's magnitude and duration. specific calculation involves other class info are for
 * Use case interact.
 */
public class Horde {
    private int magnitude;
    private double duration;

    public Horde() {
        this.magnitude = EntityConstants.STARTERHORDEMAGNITUDE;
        this.duration = EntityConstants.STARTERHORDEDURATION;
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
}
