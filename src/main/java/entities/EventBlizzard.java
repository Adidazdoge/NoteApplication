package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of a Blizzard. Represents a harsh blizzard that impacts resources and morale.
 */
public class EventBlizzard implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occurringLocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;

    private final String fightOutcome; // Try to "fight" the blizzard by securing shelter
    private final String negotiateOutcome; // Attempt to negotiate (prepare supplies)
    private final String fleeOutcome; // Do nothing and let the blizzard take its toll

    public EventBlizzard() {
        this.isPositive = false; // Negative event
        this.occurringLocation = new ArrayList<>();
        occurringLocation.add(EntityConstants.ICELAND);

        this.description = "A harsh blizzard strikes, blanketing the area in ice and snow. The biting cold "
                + "and lack of visibility make survival challenging. What will you do?";
        this.choices = new HashMap<>();
        this.choices.put(EntityConstants.FIRSTCHOICE, "Secure shelter");
        this.choices.put(EntityConstants.SECONDCHOICE, "Prepare supplies");
        this.choices.put(EntityConstants.THIRDCHOICE, "Do nothing");

        this.probability = EntityConstants.RAREEVENTBASEPROB;

        this.fightOutcome = "You secured shelter from the blizzard, minimizing resource loss.";
        this.negotiateOutcome = "You prepared supplies, but the blizzard still consumed some resources.";
        this.fleeOutcome = "You did nothing, and the blizzard wreaked havoc on your group.";
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

    public ArrayList<String> getOccurringLocation() {
        return occurringLocation;
    }

    public String getFightOutcome() {
        return fightOutcome;
    }

    public String getNegotiateOutcome() {
        return negotiateOutcome;
    }

    public String getFleeOutcome() {
        return fleeOutcome;
    }
}
