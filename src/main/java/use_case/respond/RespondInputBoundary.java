package use_case.respond;

/**
 * Input boundary of respond use case.
 */
public interface RespondInputBoundary {

    /**
     * Execute method for respond.
     * @param inputdata player's input, represented in input data.
     */
    void execute(RespondInputData inputdata);
}
