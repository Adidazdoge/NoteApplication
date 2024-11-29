package usecases.startallowcate;

import entities.EntityConstants;

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
        boolean failureOccurred = false;

        int points = allowcateDataAccessInterface.getPlayerAttributes().getPoints();
        int social = allowcateDataAccessInterface.getPlayerAttributes().getSocial();
        int luck = allowcateDataAccessInterface.getPlayerAttributes().getLuck();
        int mobilization = allowcateDataAccessInterface.getPlayerAttributes().getMobilization();
        int thrift = allowcateDataAccessInterface.getPlayerAttributes().getThrift();
        int generalship = allowcateDataAccessInterface.getPlayerAttributes().getGeneralship();

        // Check if points are available
        if (points <= 0) {
            failmessage = "Not enough Attribute points.";
            allowcateOutputBoundary.preparefailureview(failmessage);
            failureOccurred = true;
        }
        else if (inputdata.getAttribute().equals(EntityConstants.SOCIAL)) {
            points = points - 1;
            social = social + 1;
        }
        else if (inputdata.getAttribute().equals(EntityConstants.LUCK)) {
            points = points - 1;
            luck = luck + 1;
        }
        else if (inputdata.getAttribute().equals(EntityConstants.MOBILIZATION)) {
            points = points - 1;
            mobilization = mobilization + 1;
        }
        else if (inputdata.getAttribute().equals(EntityConstants.THRIFT)) {
            points = points - 1;
            thrift = thrift + 1;
        }
        else if (inputdata.getAttribute().equals(EntityConstants.GENERALSHIP)) {
            points = points - 1;
            generalship = generalship + 1;
        }
        else {
            // Invalid attribute
            failmessage = "Invalid attribute chosen.";
            allowcateOutputBoundary.preparefailureview(failmessage);
            failureOccurred = true;
        }

        // If no failure occurred, update attributes and prepare success view
        if (!failureOccurred) {
            successhelper(points, social, luck, mobilization, thrift, generalship);
        }
    }

    private void successhelper(int points, int social, int luck, int mobilization, int thrift, int generalship) {
        allowcateDataAccessInterface.getPlayerAttributes().setPoints(points);
        allowcateDataAccessInterface.getPlayerAttributes().setSocial(social);
        allowcateDataAccessInterface.getPlayerAttributes().setLuck(luck);
        allowcateDataAccessInterface.getPlayerAttributes().setMobilization(mobilization);
        allowcateDataAccessInterface.getPlayerAttributes().setThrift(thrift);
        allowcateDataAccessInterface.getPlayerAttributes().setGeneralship(generalship);
        final AllowcateOutputData outputdata = new AllowcateOutputData(points, social,
                luck, mobilization, thrift, generalship);
        allowcateOutputBoundary.preparesuccessview(outputdata);
    }
}
