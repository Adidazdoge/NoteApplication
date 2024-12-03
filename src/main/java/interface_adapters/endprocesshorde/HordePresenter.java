package interface_adapters.endprocesshorde;

import interface_adapters.NavigationManager;
import usecases.endprocesshorde.HordeOutputBoundary;
import usecases.endprocesshorde.HordeOutputData;

/**
 * Horde presenter.
 */
public class HordePresenter implements HordeOutputBoundary {
    private final HordeInterface newview;
    private final NavigationManager navigationManager;
    private final HordeInterfaceNavigate view;

    public HordePresenter(HordeInterface newview, NavigationManager navigationManager, HordeInterfaceNavigate view) {
        this.newview = newview;
        this.navigationManager = navigationManager;
        this.view = view;
    }

    @Override
    public void prepareSuccessView(HordeOutputData outputData) {
        view.NavigateHordeGameOver(navigationManager);
        newview.updateUiHorde(outputData.getDescription(), outputData.getScore());
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        newview.failureHorde(errorMessage);
    }
}
