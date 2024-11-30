package usecases.dailymove;

public class MoveOutputData {

    private final int newx;
    private final int newy;
    private final boolean usecasesuccess;
    private final String message;

    public MoveOutputData(int newx, int newy, boolean usecasesuccess, String message) {
        this.newx = newx;
        this.newy = newy;
        this.usecasesuccess = usecasesuccess;
        this.message = message;
    }

    public int getNewx() {
        return newx;
    }

    public int getNewy() {
        return newy;
    }

    public boolean isUsecasesuccess() {
        return usecasesuccess;
    }

    /**
     * Return the success message description to player.
     * @return message
     */
    public String getmessage() {
        return message;
    }
}
