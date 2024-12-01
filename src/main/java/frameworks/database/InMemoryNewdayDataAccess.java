package frameworks.database;

import entities.*;
import usecases.gamenewday.NewdayDataAccessInterface;

public class InMemoryNewdayDataAccess implements NewdayDataAccessInterface {
    private final PlayerInfo playerInfo;
    private final Inventory inventory;
    private final Location location;
    private final PlayerAttributes playerAttributes;
    private final Horde horde;

    public InMemoryNewdayDataAccess(PlayerInfo playerInfo, Inventory inventory, Location location,
                                    PlayerAttributes playerAttributes, Horde horde) {
        this.playerInfo = playerInfo;
        this.inventory = inventory;
        this.location = location;
        this.playerAttributes = playerAttributes;
        this.horde = horde;
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void setDaysSurvived(int days) {
        playerInfo.setDaysSurvived(days);
    }

    @Override
    public void setScore(int score) {
        playerInfo.setScore(score);
    }

    @Override
    public void changeFood(int food) {
        inventory.changeFood(food);
    }

    @Override
    public void changeWater(int water) {
        inventory.changeWater(water);
    }

    @Override
    public void changePeople(int people) {
        inventory.changePeople(people);
    }

    @Override
    public void changeWeapon(int weapon) {
        inventory.changeweapon(weapon);
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    @Override
    public Horde getHorde() {
        return horde;
    }
}
