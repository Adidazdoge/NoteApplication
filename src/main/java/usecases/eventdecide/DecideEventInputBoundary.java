package usecases.eventdecide;

/**
 * Input boundary of decide event, will have execute based on input data provided, in this case, input data won't be
 * necessary as this is system automatic processing to the next day.
 */
public interface DecideEventInputBoundary {
    /**
     * Execute the decide event, which takes list of event from DAO, and inputdata.
     * @param inputdata inputdata type.
     */
    void execute(DecideEventInputData inputdata);
}
