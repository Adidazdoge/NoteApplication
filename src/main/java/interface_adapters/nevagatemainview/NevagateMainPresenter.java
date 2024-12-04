package interface_adapters.nevagatemainview;

import interface_adapters.NavigationManager;
import usecases.navigateMain.NevagateMainOutputBoundary;

public class NevagateMainPresenter implements NevagateMainOutputBoundary {
    private NavigationManager navigationManager;

    public NevagateMainPresenter(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void prepareMainView() {
        navigationManager.showMainView();
    }
}
