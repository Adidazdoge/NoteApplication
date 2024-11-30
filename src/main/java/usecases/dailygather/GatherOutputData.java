package usecases.dailygather;

/**
 * Outputdata for the new view, notice the classes are already updated in execution of interactor, so this is just
 * for the view, displaying successmessage as summary of the gather like "Your group found 178 food, 90 water, 10 weapon
 * etc" .
 */
public class GatherOutputData {
    private String successmessage;
    private int updatedfood;
    private int updatedwater;
    private int updatedweapon;

    public GatherOutputData(String successmessage, int updatedfood, int updatedwater, int updatedweapon) {
        this.successmessage = successmessage;
        this.updatedfood = updatedfood;
        this.updatedwater = updatedwater;
        this.updatedweapon = updatedweapon;
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public int getUpdatedfood() {
        return updatedfood;
    }

    public int getUpdatedwater() {
        return updatedwater;
    }

    public int getUpdatedweapon() {
        return updatedweapon;
    }
}
