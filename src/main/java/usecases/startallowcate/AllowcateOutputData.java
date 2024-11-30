package usecases.startallowcate;

/**
 * Outputdata type for the view side, which contain newly updated attributes and points left for them to update view.
 */
public class AllowcateOutputData {
    private int point;
    private int social;
    private int luck;
    private int mobilization;
    private int thrift;
    private int generalship;

    public AllowcateOutputData(int point, int social, int luck, int mobilization, int thrift, int generalship) {
        this.point = point;
        this.social = social;
        this.luck = luck;
        this.mobilization = mobilization;
        this.thrift = thrift;
        this.generalship = generalship;
    }

    public int getPoint() {
        return point;
    }

    public int getSocial() {
        return social;
    }

    public int getLuck() {
        return luck;
    }

    public int getMobilization() {
        return mobilization;
    }

    public int getThrift() {
        return thrift;
    }

    public int getGeneralship() {
        return generalship;
    }
}
