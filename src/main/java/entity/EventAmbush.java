package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of ambush, player decide they want to fight back, pay, escape or talk, different action have different
 * result as well as result description. However, notice I'm only doing description here ot make entity simple
 * thus avoid complex and coupling calculation in entity and leave it to use case interact to avoid coupling, and
 * making sure they process information without knowing anything about other classes.
 */

public class EventAmbush implements Event {

    private final Boolean isPositive;
    private final ArrayList<String> occuringlocation;
    private final String description;
    private final Map<Integer, String> choices;
    private double probability;
    private final String fightoutcomesuccess;
    private final String fightoutcomefailed;
    private final String payoutcome;
    private final String escapeoutcome;
    private final String negotiatesuccessoutcome;
    private final String negotiatefailedoutcome;

    public EventAmbush() {
        this.isPositive = false;
        this.occuringlocation = new ArrayList<>();
        occuringlocation.add("Plain");
        occuringlocation.add("Iceland");
        occuringlocation.add("Wood");
        occuringlocation.add("City");
        occuringlocation.add("Desert");
        this.description = "Your group is ambushed by a small band of desperate bandits demanding your "
                + "food supplies. Their ragged appearance suggests they're struggling to survive. What will you do?";
        this.choices = new HashMap<>();
        choices.put(Entityconstants.FIRSTCHOICE, "Fight back");
        choices.put(Entityconstants.SECONDCHOICE, "Pay the bandits");
        choices.put(Entityconstants.THIRDCHOICE, "Try to escape");
        choices.put(Entityconstants.FOURTHCHOICE, "Negotiate");
        this.probability = Entityconstants.COMMONEVENTBASEPROB;
        this.fightoutcomesuccess = "You successfully beat the bandits and secured their supplies.";
        this.fightoutcomefailed = "You fought hard but were overpowered, losing some supplies.";
        this.payoutcome = "You handed over some of your food to avoid conflict with the bandits.";
        this.escapeoutcome = "You managed to escape, but the chaos caused you to lose track of direction to where"
                + "your group was heading.";
        this.negotiatesuccessoutcome = "You successfully negotiated with the bandits, convincing them to leave peacefully.";
        this.negotiatefailedoutcome = "Your negotiation failed, and the bandits took a portion of your supplies by force.";
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

    public String getFightoutcomesuccess() {
        return fightoutcomesuccess;
    }

    public String getFightoutcomefailed() {
        return fightoutcomefailed;
    }

    public String getPayoutcome() {
        return payoutcome;
    }

    public String getEscapeoutcome() {
        return escapeoutcome;
    }

    public String getNegotiatesuccessoutcome() {
        return negotiatesuccessoutcome;
    }

    public String getNegotiatefailedoutcome() {
        return negotiatefailedoutcome;
    }
}
