package frameworks.database;

import java.util.ArrayList;
import java.util.List;

import entities.PlayerRankingEntry;
import interface_adapters.gateways.RankingRepository;

/**
 * An in-memory implementation of the RankingRepository interface.
 * Stores and retrieves ranking entries in memory, primarily for testing purposes.
 *
 * ----------------------------Example for how to call------------------------------
 * InMemoryRankingRepository repository = new InMemoryRankingRepository();
 * RankingUseCase useCase = new RankingUseCase(repository);
 * RankingController controller = new RankingController(useCase);
 *
 * // Display the top 3 players
 *        System.out.println("Top 3 Players:");
 *        controller.handleRankingRequest(3);
 */
public class InMemoryRankingRepository implements RankingRepository {
    private final List<PlayerRankingEntry> rankings = new ArrayList<>();

    /**
     * Constructs an InMemoryRankingRepository with a predefined set of ranking entries.
     */
    public InMemoryRankingRepository() {
        // todo
        // Database import
        rankings.add(new PlayerRankingEntry("player1", 100, 40, false));
        rankings.add(new PlayerRankingEntry("player2", 200, 60, true));
    }

    /**
     * Retrieves all ranking entries stored in memory.
     *
     * @return A list of all ranking entries.
     */
    @Override
    public List<PlayerRankingEntry> getAllRankings() {
        // Return a copy to prevent modification
        return new ArrayList<>(rankings);
    }
}