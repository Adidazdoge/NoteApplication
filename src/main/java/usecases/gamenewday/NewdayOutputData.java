package usecases.gamenewday;

/**
 * Output data of newday after is done, should return a message saying what happened, how many people gained, lose, etc.
 * Or, was the usecase a success, would will for example if already 60 days and still called this use case to day 61.
 */
public class NewdayOutputData {
    private String message;
    private boolean success;
    private String failmessage;

    public NewdayOutputData(String message, boolean success, String failmessage) {
        this.message = message;
        this.success = success;
        this.failmessage = failmessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailmessage() {
        return failmessage;
    }
}

