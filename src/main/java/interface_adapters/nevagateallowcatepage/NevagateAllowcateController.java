package interface_adapters.nevagateallowcatepage;

import usecases.nevagateAllowcatePage.NevagateAllowcateInputBoundary;
import usecases.nevagateAllowcatePage.NevagateAllowcateInputdata;

public class NevagateAllowcateController {
    private final NevagateAllowcateInputBoundary interactor;

    public NevagateAllowcateController(NevagateAllowcateInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final NevagateAllowcateInputdata inputdata = new NevagateAllowcateInputdata();
        interactor.execute(inputdata);
    }
}
