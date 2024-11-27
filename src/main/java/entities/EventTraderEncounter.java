package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of a trader encounter. The player can trade, ignore, or rob the trader,
 * each leading to specific outcomes. This class is designed to store outcomes
 * and allow interaction logic to remain in the use case layer.
 */
public class EventTraderEncounter implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringLocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private final String tradeOutcomeSuccess;
    private final String tradeOutcomeScam;
    private final String ignoreOutcome;
    private final String robOutcomeSuccess;
    private final String robOutcomeFail;

    public EventTraderEncounter() {
        this.isPositive = true; // Positive if trade succeeds, but actions can lead to negative results.
        this.occuringLocation = new ArrayList<>();
        occuringLocation.add(Entityconstants.PLAIN);
        occuringLocation.add(Entityconstants.CITY);
        occuringLocation.add(Entityconstants.DESERT);

        this.description = "You encounter a wandering trader offering supplies in exchange for your resources. "
                + "What will you do?";
        this.choices = new HashMap<>();
        choices.put(Entityconstants.FIRSTCHOICE, "Trade with the trader");
        choices.put(Entityconstants.SECONDCHOICE, "Ignore the trader");
        choices.put(Entityconstants.THIRDCHOICE, "Attempt to rob the trader");

        this.probability = Entityconstants.RAREEVENTBASEPROB;
        this.tradeOutcomeSuccess = "You traded successfully and gained valuable supplies.";
        this.tradeOutcomeScam = "The trader scammed you, taking your resources and leaving you with nothing.";
        this.ignoreOutcome = "You ignored the trader and moved on.";
        this.robOutcomeSuccess = "You successfully robbed the trader, taking their supplies.";
        this.robOutcomeFail = "The robbery attempt failed, and you lost resources in the scuffle.";
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

    public ArrayList<String> getOccuringLocation() {
        return occuringLocation;
    }

    public String getTradeOutcomeSuccess() {
        return tradeOutcomeSuccess;
    }

    public String getTradeOutcomeScam() {
        return tradeOutcomeScam;
    }

    public String getIgnoreOutcome() {
        return ignoreOutcome;
    }

    public String getRobOutcomeSuccess() {
        return robOutcomeSuccess;
    }

    public String getRobOutcomeFail() {
        return robOutcomeFail;
    }
}
