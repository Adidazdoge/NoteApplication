package usecases.nevagateLogin;

/**
 * Output Data for the NevagateLogin use case.
 */
public class NevagateLoginOutputData {
    private final String message;

    public NevagateLoginOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
