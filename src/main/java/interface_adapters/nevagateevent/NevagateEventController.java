package interface_adapters.nevagateevent;

import usecases.NevagateEventPage.NevagateEventInputBoundary;
import usecases.NevagateEventPage.NevagateEventInputdata;
import usecases.NevagateEventPage.NevagateEventInteractor;

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
