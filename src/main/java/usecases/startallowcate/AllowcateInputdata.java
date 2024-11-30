package usecases.startallowcate;

/**
 * Inputdata type, contain all we need to know from player's side,
 * which is which attribute they choose by name in string.
 * (all lowercase except first letter format as in entityconstant)
 */
public class AllowcateInputdata {
    private String attribute;

    public AllowcateInputdata(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
