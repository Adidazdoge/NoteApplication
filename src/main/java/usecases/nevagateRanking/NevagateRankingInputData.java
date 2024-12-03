package usecases.nevagateRanking;

/**
 * Input data class for the NavigateRanking use case.
 */
public class NevagateRankingInputData {
    private final String context;

    /**
     * Constructs a NevagateRankingInputData instance.
     *
     * @param context A string representing the navigation context (e.g., "Navigate to Ranking").
     */
    public NevagateRankingInputData(String context) {
        this.context = context;
    }

    /**
     * Gets the navigation context.
     *
     * @return A string representing the context.
     */
    public String getContext() {
        return context;
    }
}
