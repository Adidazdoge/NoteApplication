package interface_adapters.nevagateevent;

import usecases.nevagateEventPage.NevagateEventInputBoundary;
import usecases.nevagateEventPage.NevagateEventInputdata;

/**
 * Controller.
 */
public class NevagateEventController {
    private final NevagateEventInputBoundary interactor;

    public NevagateEventController(NevagateEventInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final NevagateEventInputdata inputdata = new NevagateEventInputdata();
        interactor.execute(inputdata);
    }
}
