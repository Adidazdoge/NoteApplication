package usecases.eventrespond.trader;

import usecases.eventrespond.trader.TraderOutputData;

/**
 * Output boundary for handling Trader event results.
 */
public interface TraderOutputBoundary {
    void prepareSuccessView(TraderOutputData outputData);
    void prepareFailureView(String errorMessage);
}
