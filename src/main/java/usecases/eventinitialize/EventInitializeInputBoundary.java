package usecases.eventinitialize;

/**
 * Input boundary of event initializer, so interactor must have an execute method uses the inputdata.
 */
public interface EventInitializeInputBoundary {
    /**
     * An automatic usecase, no input from player side is needed.
     * @param inputdata not really needed.
     */
    void execute(EventInitializeInputData inputdata);
}
