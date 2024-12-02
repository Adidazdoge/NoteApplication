package interface_adapters.navigatecharactercreationview;

import usecases.navigatecharactercreation.NavigateCharacterCreationOutputBoundary;
import usecases.navigatecharactercreation.NavigateCharacterCreationOutputData;

/**
 * Presenter for handling the response output of character creation.
 */
public class NavigateCharacterCreationPresenter implements NavigateCharacterCreationOutputBoundary {
    private final NavigateCharacterCreationInterface view;

    public NavigateCharacterCreationPresenter(NavigateCharacterCreationInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(NavigateCharacterCreationOutputData outputData) {
        view.updateUiResponse(outputData.getMessage());
    }

    public void prepareFailureView(String errorMessage) {
    }
}
