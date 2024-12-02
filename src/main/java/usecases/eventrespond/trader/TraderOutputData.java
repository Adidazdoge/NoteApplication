package usecases.eventrespond.trader;

/**
 * Output data for the Trader event, representing the outcome and resource changes.
 */
public class TraderOutputData {
    private final String message;
    private final int foodChange;
    private final int waterChange;
    private final int suppliesChange;
    private final int peopleChange;
    private final String inventoryMessage;

    public TraderOutputData(String message, int foodChange, int waterChange, int suppliesChange, int peopleChange, String inventoryMessage) {
        this.message = message;
        this.foodChange = foodChange;
        this.waterChange = waterChange;
        this.suppliesChange = suppliesChange;
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

    public int getSuppliesChange() {
        return suppliesChange;
    }

    public int getPeopleChange() {
        return peopleChange;
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }
}
