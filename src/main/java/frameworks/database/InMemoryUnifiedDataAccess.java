package frameworks.database;

import java.util.ArrayList;

import entities.*;
import usecases.dailybroadcast.BroadcastDataAccessInterface;
import usecases.dailygather.GatherDataAccessInterface;
import usecases.dailymove.MoveDataAccessInterface;
import usecases.endprocesshorde.HordeDataAccessInterface;
import usecases.eventdecide.DecideEventDataAccessInterface;
import usecases.eventinitialize.EventInitializeDataAccessInterface;
import usecases.eventrespond.shared.RespondDataAccessInterface;
import usecases.fetchresource.FetchDataAccessInterface;
import usecases.gameminimap.MinimapDataAccessInterface;
import usecases.gamenewday.NewdayDataAccessInterface;
import usecases.gameplacedescription.PlaceDescriptionDataAccessInterface;
import usecases.nevagateAllowcatePage.NevagateAllowcateDataAccessInterface;
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
        RespondDataAccessInterface,
        NevagateAllowcateDataAccessInterface {
    // Shared game data
    private PlayerAttributes playerAttributes;
    private Inventory inventory;
    private ArrayList<Event> events;
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
        this.events = events;
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

    // Implementation of AllowcateDataAccessInterface
    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
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

    @Override
    public void updateInventory(int resourceChange) {
        inventory.changeWater(resourceChange);
    }

    // Implement of the DecideEventDataAccessInterface
    @Override
    public ArrayList<Event> getEvents() {
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
    public void setEvents(ArrayList<Event> events) {
        this.events = new ArrayList<>(events);
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    // Implement of the EventInitiallizeInterface
    @Override
    public Event getEvent() {
        return events.get(0);
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
}
