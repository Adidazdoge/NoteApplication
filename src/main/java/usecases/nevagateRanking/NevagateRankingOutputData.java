package usecases.nevagateRanking;

/**
 * Output data class for the NavigateRanking use case.
 * Encapsulates the results of the navigation process.
 */
public class NevagateRankingOutputData {
    private final String message;

    /**
     * Constructs a NevagateRankingOutputData instance.
     *
     * @param message A message indicating the navigation result.
     */
    public NevagateRankingOutputData(String message) {
        this.message = message;
    }

    /**
     * Gets the navigation result message.
     *
     * @return A string containing the result message.
     */
    public String getMessage() {
        return message;
    }
}
