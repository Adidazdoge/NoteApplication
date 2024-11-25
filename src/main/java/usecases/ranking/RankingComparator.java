package usecases.ranking;

import java.util.Comparator;

import entities.RankingEntry;

/**
 * Comparator for sorting RankingEntry objects.
 *
 * Players who have won the game (won = true) are ranked first.
 * Within the groups of winners and non-winners, players are ranked by their scores in descending order.
 */
public class RankingComparator implements Comparator<RankingEntry> {

    /**
     * Compares two RankingEntry objects for ranking purposes.
     *
     * The comparison logic prioritizes players who have won the game (won = true).
     * Within the same group (both won or both not won), players are sorted by their scores in descending order.
     *
     * @param a The first RankingEntry to compare.
     * @param b The second RankingEntry to compare.
     * @return A negative integer, zero, or a positive integer as the first entry
     *         is ranked higher, equally, or lower than the second entry.
     */
    @Override
    public int compare(RankingEntry a, RankingEntry b) {
        // Step 1: Compare by "won" status (true > false)
        if (a.isWon() && !b.isWon()) {
            return -1;
        }
        else if (!a.isWon() && b.isWon()) {
            return 1;
        }

        // Step 2: Compare by score (descending order)
        return Integer.compare(b.getScore(), a.getScore());
    }
}
