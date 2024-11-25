package frameworks.database;

import java.util.ArrayList;
import java.util.List;

import entities.RankingEntry;
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
    private final List<RankingEntry> rankings = new ArrayList<>();

    /**
     * Constructs an InMemoryRankingRepository with a predefined set of ranking entries.
     */
    public InMemoryRankingRepository() {
        // todo
        // rankings.add(new RankingEntry("player1", 100));
        // rankings.add(new RankingEntry("player2", 200));
        // rankings.add(new RankingEntry("player3", 150));
        // rankings.add(new RankingEntry("player4", 250));
    }

    /**
     * Retrieves all ranking entries stored in memory.
     *
     * @return A list of all ranking entries.
     */
    @Override
    public List<RankingEntry> getAllRankings() {
        // Return a copy to prevent modification
        return new ArrayList<>(rankings);
    }
}
