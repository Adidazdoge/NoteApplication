package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of a flood. This is a negative event that occurs in Plains, Woods, or Cities.
 * The flood causes a loss of resources or requires immediate action to mitigate damages.
 */
public class EventFlood implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringlocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private final String evacuateOutcome;
    private final String secureSuppliesOutcome;
    private final String doNothingOutcome;

    public EventFlood() {
        this.isPositive = false;
        this.occuringlocation = new ArrayList<>();
        occuringlocation.add(EntityConstants.PLAIN);
        occuringlocation.add(EntityConstants.FOREST);
        occuringlocation.add(EntityConstants.CITY);

        this.description = "Heavy rains have caused nearby rivers to overflow, threatening your group with a flood. "
                + "You must act quickly to avoid losing supplies and people. What will you do?";
        this.choices = new HashMap<>();
        choices.put(EntityConstants.FIRSTCHOICE, "Evacuate");
        choices.put(EntityConstants.SECONDCHOICE, "Secure supplies");
        choices.put(EntityConstants.THIRDCHOICE, "hope subsides");

        this.probability = EntityConstants.RAREEVENTBASEPROB;
        this.evacuateOutcome = "You successfully evacuated, saving most of your group but losing some supplies.";
        this.secureSuppliesOutcome = "You secured your supplies but lost time and some members to the flood.";
        this.doNothingOutcome = "The flood devastated your group, causing heavy losses in supplies and morale.";
    }

    @Override
    public double getprobability() {
        return this.probability;
    }

    @Override
    public void setprobability(double prob) {
        this.probability = prob;
    }

    @Override
    public Map<Integer, String> getchoices() {
        return this.choices;
    }

    @Override
    public String getdescription() {
        return this.description;
    }

    @Override
    public boolean getispositive() {
        return isPositive;
    }

    @Override
    public ArrayList<String> getOccuringlocation() {
        return occuringlocation;
    }

    public String getEvacuateOutcome() {
        return evacuateOutcome;
    }

    public String getSecureSuppliesOutcome() {
        return secureSuppliesOutcome;
    }

    public String getDoNothingOutcome() {
        return doNothingOutcome;
    }

    /**
     * Return the name of this event.
     * @return the event name.
     */
    public String toString() {
        return "Flood";
    }
}
