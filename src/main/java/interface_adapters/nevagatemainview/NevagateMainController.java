package interface_adapters.nevagatemainview;

import usecases.navigateMain.NavigateMainInputBoundary;
import usecases.navigateMain.NavigateMainInputData;
import usecases.navigateMain.NavigateMainInputData;

/**
 * Controller for navigating to new page.
 */
public class NevagateMainController {
    private final NavigateMainInputBoundary interacter;

    public NevagateMainController(NavigateMainInputBoundary interacter) {
        this.interacter = interacter;
    }

    public void execute() {
        final NavigateMainInputData inputdata = new NavigateMainInputData();
        interacter.execute(inputdata);
    }
}
