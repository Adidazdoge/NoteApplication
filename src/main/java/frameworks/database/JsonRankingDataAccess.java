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

    public JsonRankingDataAccess(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<PlayerRankingEntry>>() {});
        this.rankings = new ArrayList<>(database.load());
    }

    @Override
    public List<PlayerRankingEntry> getLeaderboard() {
        return new ArrayList<>(rankings);
    }

    @Override
    public void updateScore(String username, int score) {
        boolean playerFound = false;

        for (PlayerRankingEntry entry : rankings) {
            if (entry.getName().equals(username)) {
                entry.setScore(score);
                playerFound = true;
                break;
            }
        }

        if (!playerFound) {
            rankings.add(new PlayerRankingEntry(username, score, 0, false));
        }

        saveRankings();
    }

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

    private void saveRankings() {
        try {
            database.save(rankings);
            reloadData();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reloadData() throws IOException {
        this.rankings = new ArrayList<>(database.load());
    }
}

