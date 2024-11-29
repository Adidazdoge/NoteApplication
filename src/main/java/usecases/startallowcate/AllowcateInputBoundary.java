package usecases.startallowcate;

/**
 * Input boundary of allowcate, interactor implement this and should have execute method.
 */
public interface AllowcateInputBoundary {

    /**
     * Execute this, which decrement point and increment which point player chose. With output data being all newly
     * update points and attributes.
     * @param inputdata inputdata from player side, should
     */
    void execute(AllowcateInputdata inputdata);
}
