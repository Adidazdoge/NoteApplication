package use_case.gather;

/**
 * Interface of input, which implemented by according interactor.
 */
public interface GatherInputBoundary {

    /**
     * Execute the use case, with input data provided in datatype GatherInputData.
     * @param inputdata the input data which is meanness for now since this action only determined by internal data.
     */
    void execute(GatherInputData inputdata);
}

