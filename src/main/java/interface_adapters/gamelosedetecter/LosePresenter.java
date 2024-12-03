package interface_adapters.gamelosedetecter;

import interface_adapters.NavigationManager;
import usecases.gamelosedetecter.LoseOutputBoundary;
import usecases.gamelosedetecter.LoseOutputData;

public class LosePresenter implements LoseOutputBoundary {
    private NavigationManager navigationManager;
    private LoseInterface newview;
    private LoseInterfaceNavigate view;

    public LosePresenter(LoseInterface newview, NavigationManager navigationManager, LoseInterfaceNavigate view) {
        this.newview = newview;
        this.navigationManager = navigationManager;
        this.view = view;
    }

    @Override
    public void preapareGameoverEarly(LoseOutputData loseOutputData) {
        view.navigateGameOver(navigationManager);
        newview.prepareGameOverEarly(loseOutputData.getLosedescription(), loseOutputData.getScore());
    }
}
