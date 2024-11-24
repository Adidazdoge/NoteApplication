package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of a blizzard. This is a negative event that only occurs in Iceland.
 * The blizzard causes a loss of food due to hunger brought on by extreme cold.
 * Players must decide how to handle the situation, with each choice having specific outcomes.
 */

public class EventBlizzard implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringlocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private String outcome;

    public EventBlizzard() {
        this.isPositive = false;
        this.occuringlocation = new ArrayList<>();
        occuringlocation.add("Iceland");

        this.description = "A harsh blizzard strikes, blanketing the area in ice and snow. The biting cold "
                + "and lack of visibility make travel dangerous, and hunger begins to take its toll";
        this.choices = new HashMap<>();
        this.probability = Entityconstants.COMMONEVENTBASEPROB;
        this.outcome = "Due to cold, your group had to consume more food";
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

    public ArrayList<String> getOccuringlocation() {
        return occuringlocation;
    }

    public String getOutcome() {
        return outcome;
    }
}
