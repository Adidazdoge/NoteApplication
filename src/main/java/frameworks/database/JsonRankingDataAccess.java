package frameworks.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.PlayerRankingEntry;
import usecases.accountranking.RankingDataAccessInterface;

/**
 * A JSON-based implementation of the RankingDataAccessInterface.
 * This class is responsible for retrieving and updating ranking data stored in a JSON file.
 */
public class JsonRankingDataAccess implements RankingDataAccessInterface {
    private final FileDatabase<PlayerRankingEntry> database;
    private List<PlayerRankingEntry> rankings;

    /**
     * Constructs a new JsonRankingDataAccess with the specified JSON file path.
     *
     * @param filePath The path to the JSON file storing the rankings.
     * @throws IOException If there is an error reading the JSON file.
     */
    public JsonRankingDataAccess(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<PlayerRankingEntry>>() {});
        this.rankings = database.load();
    }

    /**
     * Retrieves the list of ranked players.
     *
     * @return A list of PlayerRankingEntry objects representing the leaderboard.
     */
    @Override
    public List<PlayerRankingEntry> getLeaderboard() {
        return new ArrayList<>(rankings);
    }

    /**
     * Updates the score for a specific player in the leaderboard.
     *
     * @param username The username of the player.
     * @param score    The new score to assign.
     */
    @Override
    public void updateScore(String username, int score) {
        for (PlayerRankingEntry entry : rankings) {
            if (entry.getName().equals(username)) {
                entry.setScore(score);
                saveRankings();
                return;
            }
        }

        // If the player does not exist, add them to the rankings
        rankings.add(new PlayerRankingEntry(username, score, 0, false));
        saveRankings();
    }

    /**
     * Updates the full ranking entry for a player or adds a new entry if the player does not exist.
     *
     * @param username     The username of the player.
     * @param score        The player's final score.
     * @param daysSurvived The number of days survived by the player.
     * @param won          Whether the player won the game.
     */
    @Override
    public void updateRankingData(String username, int score, int daysSurvived, boolean won) {
        boolean playerFound = false;

        for (PlayerRankingEntry entry : rankings) {
            if (entry.getName().equals(username)) {
                entry.setScore(score);
                entry.setDaysSurvived(daysSurvived);
                entry.setWon(won);
                playerFound = true;
                break;
            }
        }

        if (!playerFound) {
            rankings.add(new PlayerRankingEntry(username, score, daysSurvived, won));
        }

        saveRankings();
    }

    /**
     * Saves the updated rankings to the JSON file.
     */
    private void saveRankings() {
        try {
            database.save(rankings);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
