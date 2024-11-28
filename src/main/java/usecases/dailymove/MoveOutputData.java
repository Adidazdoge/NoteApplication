package usecases.dailymove;

public class MoveOutputData {

    private final int newx;
    private final int newy;
    private final boolean usecasesuccess;
    private final String failmessage;

    public MoveOutputData(int newx, int newy, boolean usecasesuccess, String failmessage) {
        this.newx = newx;
        this.newy = newy;
        this.usecasesuccess = usecasesuccess;
        this.failmessage = failmessage;
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

    public String getFailmessage() {
        return failmessage;
    }
}
