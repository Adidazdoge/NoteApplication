package interface_adapters.gamelosedetecter;

import usecases.gamelosedetecter.LoseOutputBoundary;
import usecases.gamelosedetecter.LoseOutputData;

public class LosePresenter implements LoseOutputBoundary {
    private LoseInterface view;

    public LosePresenter(LoseInterface view) {
        this.view = view;
    }

    @Override
    public void preapareGameoverEarly(LoseOutputData loseOutputData) {
        view.prepareGameOverEarly(loseOutputData.getLosedescription());
    }
}
