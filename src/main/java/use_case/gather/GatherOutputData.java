package use_case.gather;

/**
 * Outputdata for the new view, notice the classes are already updated in execution of interactor, so this is just
 * for the view, displaying successmessage as summary of the gather like "Your group found 178 food, 90 water, 10 weapon
 * etc" .
 */
public class GatherOutputData {
    private String successmessage;
    private String errormessage;

    public GatherOutputData(String successmessage, String errormessage) {
        this.successmessage = successmessage;
        this.errormessage = errormessage;
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public String getErrorMessage() {
        return errormessage;
    }
}
