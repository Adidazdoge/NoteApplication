package interface_adapters.gateways;

import java.util.List;

import entities.PlayerRankingEntry;

/**
 * Represents the interface for accessing ranking data.
 * This interface defines the contract for retrieving the ranking list.
 */
public interface RankingRepository {
    /**
     * Retrieves all ranking entries from the data source.
     *
     * @return A list of all ranking entries.
     */
    List<PlayerRankingEntry> getAllRankings();
}
