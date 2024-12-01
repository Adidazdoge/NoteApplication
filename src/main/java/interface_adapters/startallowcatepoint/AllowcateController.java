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
     * @param generalship generalship player choosed.
     * @param mobilization mobilization player choosed.
     * @param thrift thrfit player choosed.
     * @param luck luck.
     * @param social social.
     * @param point point.
     */
    public void execute(int point, int social, int luck, int thrift, int mobilization, int generalship) {
        final AllowcateInputdata inputdata = new AllowcateInputdata(point, social,
                luck, thrift, mobilization, generalship);
        interactor.execute(inputdata);
    }
}
