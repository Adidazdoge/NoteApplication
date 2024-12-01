package frameworks.database;

import entities.Event;
import entities.Inventory;
import entities.PlayerAttributes;
import entities.PlayerLocation;
import usecases.eventrespond.shared.RespondDataAccessInterface;

/**
 * In-Memory implementation of RespondDataAccessInterface.
 */
public class InMemoryRespondDataAccess implements RespondDataAccessInterface {
    private Event event;
    private final Inventory inventory;
    private final PlayerAttributes playerAttributes;
    private final PlayerLocation playerLocation;

    public InMemoryRespondDataAccess(Event event, Inventory inventory,
                                     PlayerAttributes playerAttributes, PlayerLocation playerLocation) {
        this.event = event;
        this.inventory = inventory;
        this.playerAttributes = playerAttributes;
        this.playerLocation = playerLocation;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    @Override
    public void changePeople(int peoplechange) {
        inventory.changePeople(peoplechange);
    }

    @Override
    public void changeFood(int foodchange) {
        inventory.changeFood(foodchange);
    }

    @Override
    public void changeWater(int waterchange) {
        inventory.changeWater(waterchange);
    }

    @Override
    public void changeWeapon(int weaponchange) {
        inventory.changeweapon(weaponchange);
    }

    @Override
    public void setplayerxcoor(int newx) {
        playerLocation.setXcoordinate(newx);
    }

    @Override
    public void setplayerycoor(int newy) {
        playerLocation.setYcoordinate(newy);
    }
}
