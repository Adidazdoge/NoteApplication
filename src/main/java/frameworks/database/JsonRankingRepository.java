package frameworks.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.PlayerRankingEntry;
import interface_adapters.gateways.RankingRepository;

/**
 * A JSON-based implementation of the RankingRepository interface.
 */

public class JsonRankingRepository implements RankingRepository {
    private final FileDatabase<PlayerRankingEntry> database;
    private List<PlayerRankingEntry> rankings;

    public JsonRankingRepository(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<PlayerRankingEntry>>() { });
        this.rankings = database.load();
    }

    @Override
    public List<PlayerRankingEntry> getAllRankings() {
        return new ArrayList<>(rankings);
    }
    /**
     * Adds a new ranking entry to the repository and persists it to the database.
     *
     * @param rankingEntry The {@link PlayerRankingEntry} object to be added to the rankings list.
     */

    public void addRanking(PlayerRankingEntry rankingEntry) {
        rankings.add(rankingEntry);
        try {
            database.save(rankings);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
