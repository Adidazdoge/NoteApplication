package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of encountering a trader. This is a neutral event that can have positive or negative outcomes
 * based on the player's choices.
 */
public class EventTraderEncounter implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringlocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private final String tradeOutcomeSuccess;
    private final String tradeOutcomeScam;
    private final String ignoreOutcome;

    public EventTraderEncounter() {
        this.isPositive = true; // Initially positive as it provides opportunities, but risks exist
        this.occuringlocation = new ArrayList<>();
        occuringlocation.add("Plain");
        occuringlocation.add("City");
        occuringlocation.add("Desert");

        this.description = "You come across a wandering trader offering supplies in exchange for some of your "
                + "resources. The trader seems trustworthy, but appearances can be deceiving. What will you do?";
        this.choices = new HashMap<>();
        choices.put(Entityconstants.FIRSTCHOICE, "Trade with the trader");
        choices.put(Entityconstants.SECONDCHOICE, "Refuse and move on");
        choices.put(Entityconstants.THIRDCHOICE, "Attempt to rob the trader");

        this.probability = Entityconstants.COMMONEVENTBASEPROB;
        this.tradeOutcomeSuccess = "The trade was successful! You gained valuable supplies.";
        this.tradeOutcomeScam = "The trader scammed you, taking your resources and leaving you with nothing.";
        this.ignoreOutcome = "You avoided the trader and continued your journey unscathed.";
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

    public String getTradeOutcomeSuccess() {
        return tradeOutcomeSuccess;
    }

    public String getTradeOutcomeScam() {
        return tradeOutcomeScam;
    }

    public String getIgnoreOutcome() {
        return ignoreOutcome;
    }
}
