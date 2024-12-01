package usecases.startallowcate;

/**
 * Inputdata type, contain all we need to know from player's side,
 * which is which attribute they choose by name in string.
 * (all lowercase except first letter format as in entityconstant)
 */
public class AllowcateInputdata {
    private int point;
    private int social;
    private int luck;
    private int thrift;
    private int mobilization;
    private int generalship;

    public AllowcateInputdata(int point, int social, int luck, int thrift, int mobilization, int generalship) {
        this.point = point;
        this.social = social;
        this.luck = luck;
        this.thrift = thrift;
        this.mobilization = mobilization;
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

    public int getThrift() {
        return thrift;
    }

    public int getMobilization() {
        return mobilization;
    }

    public int getGeneralship() {
        return generalship;
    }
}
