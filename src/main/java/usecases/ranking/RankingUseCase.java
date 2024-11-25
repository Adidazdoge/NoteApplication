package usecases.ranking;

import java.util.List;

import entities.RankingEntry;
import interface_adapters.gateways.RankingRepository;

/**
 * Handles the business logic for retrieving and sorting the ranking list.
 * Retrieves the top N players from the repository, sorted by score in descending order.
 */
public class RankingUseCase {
    // The repository to access ranking data
    private final RankingRepository repository;

    /**
     * Constructs a new RankingUseCase with the specified ranking repository.
     *
     * @param repository The repository to access ranking data.
     */
    public RankingUseCase(RankingRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the ranking request to retrieve the top N players.
     *
     * @param request The ranking request specifying the number of top players to retrieve.
     * @return A RankingResponse containing the sorted list of top-ranking players.
     */
    public RankingResponse execute(RankingRequest request) {
        final List<RankingEntry> rankings = repository.getAllRankings();
        // Sort by score descending
        rankings.sort(new RankingComparator());

        // Return the top N players based on the request
        final List<RankingEntry> topRankings = rankings.subList(0, Math.min(request.getTopN(), rankings.size()));

        return new RankingResponse(topRankings);
    }
}
