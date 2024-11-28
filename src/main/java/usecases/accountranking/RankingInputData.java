package usecases.accountranking;

/**
 * Data class for encapsulating input data for the ranking use case.
 * Contains the number of top players to retrieve.
 */
public class RankingInputData {
    // Number of top players to retrieve.
    private final int topN;

    /**
     * Constructs a new RankingInputData object with the specified number of top players.
     *
     * @param topN The number of top players to retrieve.
     */
    public RankingInputData(int topN) {
        this.topN = topN;
    }

    /**
     * Retrieves the number of top players to retrieve.
     *
     * @return The number of top players.
     */
    public int getTopN() {
        return topN;
    }
}
