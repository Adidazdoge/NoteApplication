package usecases.navigatecharactercreation;

/**
 * Input data for character creation.
 */
public class CharacterCreationInputData {
    private int social;
    private int luck;
    private int mobilization;
    private int thrift;
    private int generalship;

    public CharacterCreationInputData(int social, int luck, int mobilization, int thrift, int generalship) {
        this.social = social;
        this.luck = luck;
        this.mobilization = mobilization;
        this.thrift = thrift;
        this.generalship = generalship;
    }

    // Getter methods
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
