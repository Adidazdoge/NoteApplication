package usecases.accountranking;

import java.util.List;

import entities.PlayerRankingEntry;

/**
 * Interface for accessing ranking data.
 * Defines methods for retrieving and updating player rankings.
 */
public interface RankingDataAccessInterface {
    /**
     * Retrieves the list of ranked players.
     *
     * @return A list of PlayerRankingEntry objects representing the leaderboard.
     */
    List<PlayerRankingEntry> getLeaderboard();

    /**
     * Updates the score for a specific player.
     *
     * @param username The username of the player.
     * @param score The new score to assign.
     */
    void updateScore(String username, int score);
}
