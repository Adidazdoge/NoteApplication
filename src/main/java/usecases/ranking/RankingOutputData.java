package usecases.ranking;

import java.util.List;

import entities.PlayerRankingEntry;

/**
 * Data class for encapsulating output data from the ranking use case.
 * Contains the list of ranked players and their respective details.
 */
public class RankingOutputData {
    private final List<PlayerRankingEntry> rankings;

    /**
     * Constructs a new RankingOutputData object with the specified rankings.
     *
     * @param rankings A list of PlayerRankingEntry objects representing the leaderboard.
     */
    public RankingOutputData(List<PlayerRankingEntry> rankings) {
        this.rankings = rankings;
    }

    /**
     * Retrieves the list of ranked players.
     *
     * @return A list of PlayerRankingEntry objects.
     */
    public List<PlayerRankingEntry> getRankings() {
        return rankings;
    }
}
