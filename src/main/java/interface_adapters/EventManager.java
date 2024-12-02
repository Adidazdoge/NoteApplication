package interface_adapters;

import interface_adapters.eventrespond.ambush.AmbushResponseController;
import interface_adapters.eventrespond.blizzard.BlizzardResponseController;
import interface_adapters.eventrespond.flood.FloodResponseController;
import interface_adapters.eventrespond.survivor.SurvivorResponseController;
import interface_adapters.eventrespond.trader.TraderResponseController;

public class EventManager {
    private String ActiveEvent;
    private final AmbushResponseController ambushResponseController;
    private final BlizzardResponseController blizzardResponseController;
    private final FloodResponseController floodResponseController;
    private final SurvivorResponseController survivorResponseController;
    private final TraderResponseController traderResponseController;

    public EventManager(AmbushResponseController ambushResponseController,
                        BlizzardResponseController blizzardResponseController,
                        FloodResponseController floodResponseController,
                        SurvivorResponseController survivorResponseController,
                        TraderResponseController traderResponseController) {
        this.ActiveEvent = "";
        this.ambushResponseController = ambushResponseController;
        this.blizzardResponseController = blizzardResponseController;
        this.floodResponseController = floodResponseController;
        this.survivorResponseController = survivorResponseController;
        this.traderResponseController = traderResponseController;
    }
    public void setActiveEvent(String activeEvent) {
        this.ActiveEvent = activeEvent;
    }

    public void execute(int choice) {
        if (ActiveEvent.equals("Ambush")) {
            System.out.println("processing ambush");
            ambushResponseController.execute(choice);
        }
        else if (ActiveEvent.equals("Blizzard")) {
            System.out.println("processing blizzard");
            blizzardResponseController.execute(choice);
        }
        else if (ActiveEvent.equals("Flood")) {
            System.out.println("Processing flood");
            floodResponseController.execute(choice);
        }
        else if (ActiveEvent.equals("SurvivorsJoins")) {
            System.out.println("processing survivor");
            survivorResponseController.execute(choice);
        }
        else if (ActiveEvent.equals("TradeEncounter")) {
            System.out.println("processing trader");
            traderResponseController.execute(choice);
        }
        else {
            System.out.println("Unknown event: " + ActiveEvent);
        }
    }
}
