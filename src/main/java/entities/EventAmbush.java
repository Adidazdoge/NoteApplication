package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event of ambush, player decide they want to fight back, pay, or negotiate. Different actions have different
 * results as well as result descriptions. This entity is designed to remain simple, avoiding coupling and
 * complex logic, which is handled in the use case interactor.
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
        choices.put(EntityConstants.FIRSTCHOICE, "Fight back");
        choices.put(EntityConstants.SECONDCHOICE, "Pay the bandits");
        choices.put(EntityConstants.THIRDCHOICE, "Negotiate");
        this.probability = EntityConstants.COMMONEVENTBASEPROB;
        this.fightoutcomesuccess = "You successfully beat the bandits and secured their supplies.";
        this.fightoutcomefailed = "You fought hard but were overpowered, losing some supplies.";
        this.payoutcome = "You handed over some of your food to avoid conflict with the bandits.";
        this.negotiatesuccessoutcome = "You successfully negotiated with the bandits, convincing"
                + " them to leave peacefully.";
        this.negotiatefailedoutcome = "Your negotiation failed, and the bandits"
                + " took a portion of your supplies by force.";
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

    public String getNegotiatesuccessoutcome() {
        return negotiatesuccessoutcome;
    }

    public String getNegotiatefailedoutcome() {
        return negotiatefailedoutcome;
    }
}
