package usecases.startallowcate;

/**
 * Allowcate interactor, decrease point by one, add attribute select by one, if point was greater than 0.
 * If points are less than 0, fail and return fail message.
 */
public class AllowcateInteractor implements AllowcateInputBoundary {
    private AllowcateDataAccessInterface allowcateDataAccessInterface;
    private AllowcateOutputBoundary allowcateOutputBoundary;

    public AllowcateInteractor(AllowcateDataAccessInterface allowcateDataAccessInterface,
                               AllowcateOutputBoundary allowcateOutputBoundary) {
        this.allowcateDataAccessInterface = allowcateDataAccessInterface;
        this.allowcateOutputBoundary = allowcateOutputBoundary;

    }

    @Override
    public void execute(AllowcateInputdata inputdata) {
        String failmessage = " ";

        // Check if points are available
        if (inputdata.getPoint() < 0) {
            failmessage = "Not enough Attribute points.";
            allowcateOutputBoundary.preparefailureview(failmessage);
        }
        else {
            allowcateDataAccessInterface.setPoint(inputdata.getPoint());
            allowcateDataAccessInterface.setSocial(inputdata.getSocial());
            allowcateDataAccessInterface.setLuck(inputdata.getLuck());
            allowcateDataAccessInterface.setMobilization(inputdata.getMobilization());
            allowcateDataAccessInterface.setThrift(inputdata.getThrift());
            allowcateDataAccessInterface.setGeneralship(inputdata.getGeneralship());
            final AllowcateOutputData outputData = new AllowcateOutputData(inputdata.getPoint(), inputdata.getSocial(),
                    inputdata.getLuck(), inputdata.getMobilization(), inputdata.getThrift(),
                    inputdata.getGeneralship());
            allowcateOutputBoundary.preparesuccessview(outputData);
        }
    }
}
