package interface_adapters.nevagateevent;

import usecases.NevagateEventPage.NevagateEventInputdata;
import usecases.NevagateEventPage.NevagateEventInteractor;

/**
 * Controller.
 */
public class NevagateEventController {
    private final NevagateEventInteractor interactor;

    public NevagateEventController(NevagateEventInteractor interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final NevagateEventInputdata inputdata = new NevagateEventInputdata();
        interactor.execute(inputdata);
    }
}
