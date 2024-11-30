package usecases.eventrespond;

/**
 * Output data for the player's respond.
 */
public class RespondOutputData {
    private final String message;
    private final int peoplechange;
    private final int foodchange;
    private final int waterchange;
    private final int weaponchange;
    private final String inventorymessage;

    public RespondOutputData(String message,
                             int foodchange, int waterchange, int weaponchange, int peoplechange,
                             String inventorymessage) {
        this.message = message;
        this.peoplechange = peoplechange;
        this.foodchange = foodchange;
        this.waterchange = waterchange;
        this.weaponchange = weaponchange;
        this.inventorymessage = inventorymessage;

    }

    public String getMessage() {
        return message;
    }

    public int getPeoplechange() {
        return peoplechange;
    }

    public int getFoodchange() {
        return foodchange;
    }

    public int getWaterchange() {
        return waterchange;
    }

    public int getWeaponchange() {
        return weaponchange;
    }

    public String getInventorymessage() {
        return inventorymessage;
    }

}
