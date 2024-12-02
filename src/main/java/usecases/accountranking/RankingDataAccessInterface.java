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

    /**
     * Updates the ranking data for a specific player.
     * If the player already exists in the ranking, their data is updated.
     * If not, a new entry is created for the player.
     *
     * @param username     The username of the player.
     * @param score        The final score of the player.
     * @param daysSurvived The number of days the player survived in the game.
     * @param won          Whether the player won the game.
     */
    void updateRankingData(String username, int score, int daysSurvived, boolean won);
}
