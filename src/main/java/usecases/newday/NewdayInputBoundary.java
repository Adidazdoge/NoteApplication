package usecases.newday;

/**
 * Input boundary of the newday, so interactor must have this execute method.
 */
public interface NewdayInputBoundary {
    /**
     * Execute the move for player based on given direction.
     * @param inputdata execute, based on inputdata of player's input given.
     *                  But actually, no inputdata is needed except for the fact player press the key for next day/
     */
    void execute(NewdayInputData inputdata);
}
