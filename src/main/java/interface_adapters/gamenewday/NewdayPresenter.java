package interface_adapters.gamenewday;

import usecases.gamenewday.NewdayOutputBoundary;
import usecases.gamenewday.NewdayOutputData;

/**
 * Newday presentor,
 */
public class NewdayPresenter implements NewdayOutputBoundary {
    private NewdayInterface view;

    public NewdayPresenter(NewdayInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(NewdayOutputData outputData) {

    }

    @Override
    public void prepareFailureView(String errorMessage) {

    }
}
