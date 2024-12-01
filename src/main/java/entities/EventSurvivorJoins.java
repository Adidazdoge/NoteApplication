package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of a group of survivors wanting to join the player. The player can choose to accept, reject, or negotiate terms
 * for joining. Different actions have different outcomes, and descriptions for each are provided. This class avoids
 * coupling logic and leaves complex calculations to the use case interactor.
 */

public class EventSurvivorJoins implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringlocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private final String acceptoutcome;
    private final String rejectoutcome;
    private final String roboutcomesuccess;
    private final String roboutcomefail;

    public EventSurvivorJoins() {
        this.isPositive = true;
        this.occuringlocation = new ArrayList<>();
        occuringlocation.add(EntityConstants.PLAIN);
        occuringlocation.add(EntityConstants.FOREST);
        occuringlocation.add(EntityConstants.CITY);
        this.description = "You encounter a small group of survivors who ask to join your group. They appear skilled "
                + "but wary. What will you do?";
        this.choices = new HashMap<>();
        choices.put(EntityConstants.FIRSTCHOICE, "Accept them into the group");
        choices.put(EntityConstants.SECONDCHOICE, "Politely reject them");
        this.probability = EntityConstants.COMMONEVENTBASEPROB;
        this.acceptoutcome = "You've accept them into the group";
        this.rejectoutcome = "You've reject them into the group";
        this.roboutcomesuccess = "You've robbed the survivor, they dear not to fight back as your group overpowered"
                + "them, taking their supplies and leaving them helpless.";
        this.roboutcomefail = "The robbery failed as they escaped.";

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

    public String getAcceptoutcome() {
        return acceptoutcome;
    }

    public String getRejectoutcome() {
        return rejectoutcome;
    }

    public String getRoboutcomesuccess() {
        return roboutcomesuccess;
    }

    public String getRoboutcomefail() {
        return roboutcomefail;
    }

    /**
     * Return the name of this event.
     * @return the event name.
     */
    public String toString() {
        return "SurvivorsJoins";
    }
}
