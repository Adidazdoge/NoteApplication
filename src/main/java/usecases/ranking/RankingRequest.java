package usecases.ranking;

/**
 * Represents a request to retrieve the ranking list.
 * Contains the number of top players to retrieve.
 */
public class RankingRequest {
    // The number of top players to retrieve
    private final int topN;

    /**
     * Constructs a new RankingRequest with the specified number of top players.
     *
     * @param topN The number of top players to retrieve.
     */
    public RankingRequest(int topN) {
        this.topN = topN;
    }

    /**
     * Gets the number of top players to retrieve.
     *
     * @return The number of top players.
     */
    public int getTopN() {
        return topN;
    }
}
