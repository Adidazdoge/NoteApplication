package interface_adapters.endprocesshorde;

import usecases.endprocesshorde.HordeOutputBoundary;
import usecases.endprocesshorde.HordeOutputData;

/**
 * Horde presenter.
 */
public class HordePresenter implements HordeOutputBoundary {
    private final HordeInterface view;

    public HordePresenter(HordeInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(HordeOutputData outputData) {
        view.updateUiHorde(outputData.getDescription(), outputData.getScore());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        view.failureHorde(errorMessage);
    }
}
