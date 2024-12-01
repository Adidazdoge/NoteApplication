package usecases.nevagateAllowcatePage;

/**
 * Input boundary for nevagate to allowcare page usecase.
 */
public interface NevagateAllowcateInputBoundary {

    /**
     * Execute, which checks can use go to next page or not, is yes call next page in presentor, is not only display
     * error message.
     * @param inputdata inputdatatype, not needed.
     */
    void execute(NevagateAllowcateInputdata inputdata);
}
