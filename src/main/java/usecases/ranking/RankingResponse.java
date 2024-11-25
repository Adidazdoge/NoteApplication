package usecases.ranking;

import java.util.List;

import entities.RankingEntry;

/**
 * Represents the response for a ranking request.
 * Contains a sorted list of top-ranking entries.
 */
public class RankingResponse {
    // The list of ranking entries
    private final List<RankingEntry> rankings;

    /**
     * Constructs a new RankingResponse with the specified ranking list.
     *
     * @param rankings The sorted list of ranking entries.
     */
    public RankingResponse(List<RankingEntry> rankings) {
        this.rankings = rankings;
    }

    /**
     * Gets the sorted list of ranking entries.
     *
     * @return The ranking list.
     */
    public List<RankingEntry> getRankings() {
        return rankings;
    }
}
