package interface_adapters.startallowcatepoint;

import usecases.startallowcate.AllowcateInputBoundary;
import usecases.startallowcate.AllowcateInputdata;

/**
 * Allowcate point controller.
 */
public class AllowcateController {
    private final AllowcateInputBoundary interactor;

    public AllowcateController(AllowcateInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute based on player's selected attribute.
     * @param attribute string fromat of which selected.
     */
    public void execute(String attribute) {
        final AllowcateInputdata inputdata = new AllowcateInputdata(attribute);
        interactor.execute(inputdata);
    }
}
