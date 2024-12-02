package interface_adapters.nevagatemainview;

import usecases.nevagatemain.NevagateMainInputBoundary;
import usecases.nevagatemain.NevagateMainInputdata;

/**
 * Controller for navigating to new page.
 */
public class NevagateMainController {
    private final NevagateMainInputBoundary interacter;

    public NevagateMainController(NevagateMainInputBoundary interacter) {
        this.interacter = interacter;
    }

    public void execute() {
        final NevagateMainInputdata inputdata = new NevagateMainInputdata();
        interacter.execute(inputdata);
    }
}