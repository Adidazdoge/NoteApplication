package usecases.newday;

import entities.Inventory;
import entities.Location;
import entities.PlayerAttributes;
import entities.PlayerInfo;

/**
 * Newday data access, require player's info because we care and need to update days and score.
 * Require inventory because this part is for updating recources, how many lose, how many found depended on people num.
 * Require player attribute because thrift is scalar to resource lost (food and water)
 */
public interface NewdayDataAccessInterface {
    /**
     * Get the player info we needed, score and days.
     * @return playerinfo datatype.
     */
    PlayerInfo getPlayerinfo();

    /**
     * Get location of there player is at, because we need temperature to calculate resource loss as well.
     * @return the player's current location, in order to get current temperature.
     */
    Location getLocation();

    /**
     * Get the player inventory we need, food water, people, firearm etc.
     * @return inventory.
     */
    Inventory getInventory();

    /**
     * Set the number of days player survived to new value. (+1)
     * @param days new days
     */
    void getDaysSurvived(int days);

    /**
     * The amount of food change during new day process.
     * @param food food changed
     */
    void changeFood(int food);

    /**
     * The amount of water change during the new day process.
     * @param water water changed
     */
    void changeWater(int water);

    /**
     * The amount of people change during the new day process.
     * @param people people changed
     */
    void changePeople(int people);

    /**
     * The amount of weapon change during the new day process.
     * @param weapon weapon changed
     */
    void changeWeapon(int weapon);

    /**
     * The player attribute, which thrift is what we care about.
     * @return return player attribute.
     */
    PlayerAttributes getPlayerAttributes();
}
