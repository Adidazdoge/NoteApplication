package entities;

import java.util.ArrayList;
import java.util.Map;

/**
 * The random events of the game, this is an interface, all events should implement this.
 */
public interface Event {

    /**
     * Method for getting the probability.
     * @return probability of this event occurring in the given location, time, etc.
     */
    double getprobability();

    /**
     * Method for setting the probability of this event.
     * @param probability probability of this event,
     */
    void setprobability(double probability);

    /**
     * Getter method for the choices of the event, in map of int, string. String is the "facial" representation
     * of the event, but actually for player's input, and response determine step, we use the int.
     * @return Choices in map of int string.
     */
    Map<Integer, String> getchoices();

    /**
     * Getter method for the brief description of the event.
     * @return brief description of this event.
     */
    String getdescription();

    /**
     * Return of this is an positive event, we need this to determine how luck affect it's probability.
     * @return Return positive or not.
     */
    boolean getispositive();

    /**
     * Return the only possible locations for this event to happen.
     * @return return the occuring locations.
     */
    ArrayList<String> getOccuringlocation();

}
