package usecases.eventrespond.ambush;

/**
 * Output data for the Ambush event, representing the outcome and resource changes.
 */
public class AmbushOutputData {
    private final String message;
    private final int foodChange;
    private final int waterChange;
    private final int weaponChange;
    private final int peopleChange;
    private final String inventoryMessage;

    public AmbushOutputData(String message, int foodChange, int waterChange, int weaponChange, int peopleChange, String inventoryMessage) {
        this.message = message;
        this.foodChange = foodChange;
        this.waterChange = waterChange;
        this.weaponChange = weaponChange;
        this.peopleChange = peopleChange;
        this.inventoryMessage = inventoryMessage;
    }

    public String getMessage() {
        return message;
    }

    public int getFoodChange() {
        return foodChange;
    }

    public int getWaterChange() {
        return waterChange;
    }

    public int getWeaponChange() {
        return weaponChange;
    }

    public int getPeopleChange() {
        return peopleChange;
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }
}
