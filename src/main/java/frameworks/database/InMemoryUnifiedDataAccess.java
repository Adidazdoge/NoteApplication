package frameworks.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.*;
import usecases.nevagateEventPage.NevagateEventDataAccessInterface;
import usecases.dailybroadcast.BroadcastDataAccessInterface;
import usecases.dailygather.GatherDataAccessInterface;
import usecases.dailymove.MoveDataAccessInterface;
import usecases.endprocesshorde.HordeDataAccessInterface;
import usecases.eventdecide.DecideEventDataAccessInterface;
import usecases.eventinitialize.EventInitializeDataAccessInterface;
import usecases.eventrespond.ambush.AmbushDataAccessInterface;
import usecases.eventrespond.blizzard.BlizzardDataAccessInterface;
import usecases.eventrespond.flood.FloodDataAccessInterface;
import usecases.eventrespond.survivor.SurvivorDataAccessInterface;
import usecases.eventrespond.trader.TraderDataAccessInterface;
import usecases.fetchcurrentevent.CurrentEventDataAccessInterface;
import usecases.fetchresource.FetchDataAccessInterface;
import usecases.gamelosedetecter.LoseDataAccessInterface;
import usecases.gameminimap.MinimapDataAccessInterface;
import usecases.gamenewday.NewdayDataAccessInterface;
import usecases.gameplacedescription.PlaceDescriptionDataAccessInterface;
import usecases.nevagateAllowcatePage.NevagateAllowcateDataAccessInterface;
import usecases.nevagateGame.NevagateGameDataAccessInterface;
import usecases.nevagateGameover.NevagateGameOverDataAccessInterface;
import usecases.navigateMain.NavigateMainDataAccessInterface;
import usecases.startallowcate.AllowcateDataAccessInterface;

/**
 * Unified In-Memory Data Access Layer for managing all game data.
 */
public class InMemoryUnifiedDataAccess implements
        AllowcateDataAccessInterface,
        BroadcastDataAccessInterface,
        DecideEventDataAccessInterface,
        EventInitializeDataAccessInterface,
        FetchDataAccessInterface,
        GatherDataAccessInterface,
        HordeDataAccessInterface,
        MinimapDataAccessInterface,
        MoveDataAccessInterface,
        NewdayDataAccessInterface,
        PlaceDescriptionDataAccessInterface,
        NevagateAllowcateDataAccessInterface,
        FloodDataAccessInterface,
        SurvivorDataAccessInterface,
        TraderDataAccessInterface,
        BlizzardDataAccessInterface,
        AmbushDataAccessInterface,
        NavigateMainDataAccessInterface,
        NevagateEventDataAccessInterface,
        CurrentEventDataAccessInterface,
        NevagateGameDataAccessInterface,
        LoseDataAccessInterface,
        NevagateGameOverDataAccessInterface {
    // Shared game data
    private PlayerAttributes playerAttributes;
    private Inventory inventory;
    private ArrayList<Event> unprocessedevents;
    private Location currentLocation;
    private Horde horde;
    private PlayerInfo playerInfo;
    private PlayerLocation playerLocation;
    private Maps gameMap;
    private EventAmbush ambush;
    private EventBlizzard blizzard;
    private EventFlood flood;
    private EventSurvivorJoins survivorJoins;
    private EventTraderEncounter traderEncounter;

    // Constructor to initialize shared objects
    public InMemoryUnifiedDataAccess(PlayerAttributes playerAttributes, Inventory inventory,
                                     ArrayList<Event> events, Location currentLocation,
                                     Horde horde, PlayerInfo playerInfo,
                                     PlayerLocation playerLocation, Maps gameMap, EventAmbush ambush,
                                     EventBlizzard blizzard, EventFlood flood, EventSurvivorJoins survivorJoins,
                                     EventTraderEncounter traderEncounter) {
        this.playerAttributes = playerAttributes;
        this.inventory = inventory;
        this.unprocessedevents = events;
        this.currentLocation = currentLocation;
        this.horde = horde;
        this.playerInfo = playerInfo;
        this.playerLocation = playerLocation;
        this.gameMap = gameMap;
        this.ambush = ambush;
        this.blizzard = blizzard;
        this.flood = flood;
        this.survivorJoins = survivorJoins;
        this.traderEncounter = traderEncounter;
    }

    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    /**
     * Returns player attributes as a map.
     */
    @Override
    public Map<String, Integer> getPlayerAttributesAsMap() {
        final Map<String, Integer> attributesMap = new HashMap<>();
        attributesMap.put("Social", playerAttributes.getSocial());
        attributesMap.put("Luck", playerAttributes.getLuck());
        attributesMap.put("Mobilization", playerAttributes.getMobilization());
        attributesMap.put("Thrift", playerAttributes.getThrift());
        attributesMap.put("Generalship", playerAttributes.getGeneralship());
        return attributesMap;
    }

    @Override
    public void setSocial(int social) {
        playerAttributes.setSocial(social);
    }

    @Override
    public void setLuck(int luck) {
        playerAttributes.setLuck(luck);
    }

    @Override
    public void setThrift(int thrift) {
        playerAttributes.setThrift(thrift);
    }

    @Override
    public void setMobilization(int mobilization) {
        playerAttributes.setMobilization(mobilization);
    }

    @Override
    public void setGeneralship(int generalship) {
        playerAttributes.setGeneralship(generalship);
    }

    @Override
    public void setPoint(int point) {
        playerAttributes.setPoints(point);
    }

    // Implement of the BroadcastDataAccessInterface
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    // Implement of the DecideEventDataAccessInterface
    @Override
    public ArrayList<Event> getALLEvents() {
        // Return a copy to ensure immutability
        final ArrayList<Event> allevents = new ArrayList();
        allevents.add(ambush);
        allevents.add(blizzard);
        allevents.add(flood);
        allevents.add(survivorJoins);
        allevents.add(traderEncounter);
        return allevents;
    }

    @Override
    public void setDecidedEvents(ArrayList<Event> decidedevents) {
        this.unprocessedevents = new ArrayList<>(decidedevents);
    }

    @Override
    public ArrayList<Event> getUnprocessedEvents() {
        return unprocessedevents;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public int getActionPoint() {
        return playerInfo.getActionPoint();
    }

    @Override
    public void setActionPoint(int actionPoint) {
        playerInfo.setActionPoint(actionPoint);
    }

    // Implement of the EventInitiallizeInterface
    @Override
    public Event getEvent() {
        return unprocessedevents.get(0);
    }

    @Override
    public void removeEvent() {
        this.unprocessedevents.remove(0);
    }

    // Implement of the FetchDataAccessInterface

    // Implement of the GatherInterface
    @Override
    public void decreaseResourceavailable() {
        currentLocation.decreaseresourceavailable();
    }

    @Override
    public void changeFood(int foodgathered) {
        inventory.changeFood(foodgathered);
    }

    @Override
    public void changeWater(int watergathered) {
        inventory.changeWater(watergathered);
    }

    @Override
    public void changeWeapon(int weapongathered) {
        inventory.changeweapon(weapongathered);
    }

    // Implement of the HordeInterface
    @Override
    public Horde getHorde() {
        return horde;
    }

    @Override
    public void setWon(boolean won) {
        this.playerInfo.setWon(won);
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    // Implement of the MinimapInterface

    @Override
    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    @Override
    public Maps getMaps() {
        return gameMap;
    }

    // Implement of the MoveInterface
    @Override
    public void updatePlayerLocation(int newx, int newy) {
        playerLocation.setXcoordinate(newx);
        playerLocation.setYcoordinate(newy);
        currentLocation = gameMap.getGrid().get(newy).get(newx);
    }

    // Implement of the NewdayInterface
    @Override
    public void setDaysSurvived(int days) {
        playerInfo.setDaysSurvived(days);
    }

    @Override
    public void setScore(int score) {
        playerInfo.setScore(score);
    }

    @Override
    public void changePeople(int people) {
        inventory.changePeople(people);
    }

    @Override
    public ArrayList<Event> getDecidedEvents() {
        return this.unprocessedevents;
    }
}
