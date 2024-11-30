package interface_adapters.gamenewday;

import usecases.gamenewday.NewdayInputBoundary;
import usecases.gamenewday.NewdayInputData;

/**
 * New day usecase controller.
 */
public class NewdayController {
    private NewdayInputBoundary newdayInteractor;

    public NewdayController(NewdayInputBoundary newdayInteractor) {
        this.newdayInteractor = newdayInteractor;
    }

    /**
     * Execute the newday usecase. after player successfully pressed the button.
     */
    public void execute() {
        final NewdayInputData inputData = new NewdayInputData();
        newdayInteractor.execute(inputData);
    }
}
