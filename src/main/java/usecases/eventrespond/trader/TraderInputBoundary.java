package usecases.eventrespond.trader;

/**
 * Input boundary for handling Trader event responses.
 */
public interface TraderInputBoundary {
    void execute(TraderInputData inputData);
}
