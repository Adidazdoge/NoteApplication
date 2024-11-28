package usecases.dailygather;

import entities.Inventory;
import entities.Location;

public interface GatherDataAccessInterface {

    /**
     * Get player's inventory, we need the people data for how fast gathering.
     * @return Player's inventory, have the data: people we need for calculation.
     */
    Inventory getInventory();

    /**
     * This should return the location player is currently on, based on player location x and y,
     * to be implemented on DAO.
     * @return Location of the player, contain data we need like food and water resource available scalar etc.
     */
    Location getLocation();

    /**
     * Call decreaseresourceavailable method in location object to decrease the resource scalar by day
     * after a success gather, call the according change method in inventory class.
     */
    void decreaseResourceavailable();

    /**
     * Change player's inventory food by the amount they gathered after successful gather use case.
     * @param foodgathered foodgathered for this gather.
     */
    void changefood(int foodgathered);

    /**
     * Change player's inventory water.
     * @param watergathered water found in this gather.
     */
    void changewater(int watergathered);

    /**
     * Change player's inventory weapon.
     * @param weapongathered  weapons found in this gather, in this location, by the player.
     */
    void changeweapon(int weapongathered);
}
