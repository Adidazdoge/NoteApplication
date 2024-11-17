package use_case.gather;

/**
 * Use case interactor for gather.
 */
public class GatherInteractor implements GatherInputBoundary {
    private final GatherDataAccessInterface dataAccessInterface;
    private final GatherOutputBoundary outputBoundary;

    public GatherInteractor(GatherDataAccessInterface DataAccessInterface, GatherOutputBoundary outputBoundary) {
        this.dataAccessInterface = DataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(GatherInputData inputdata) {
        final double foodscalar = dataAccessInterface.getLocation().getfoodresourceavailable();
        final double waterscalar = dataAccessInterface.getLocation().getwaterresourceavailable();
        final double weaponscalar = dataAccessInterface.getLocation().getweaponresourceavailable();
        final int people = dataAccessInterface.getInventory().getPeople();
        final int foodgathered = (int) Math.round(foodscalar * Math.sqrt((double) people));
        final int watergathered = (int) Math.round(waterscalar * Math.sqrt((double) people));
        final int weapongathered = (int) Math.round(weaponscalar * Math.sqrt((double) people));
        // not sure if this is a good way of calculation towards game balance, I'll need when game can fully run to
        // decide on the change.
        StringBuilder successoutputmessage = new StringBuilder("Your group found ");
        if (foodgathered > 0) {
            successoutputmessage.append(foodgathered).append(" units of food ");
        }
        if (watergathered > 0) {
            successoutputmessage.append(watergathered).append(" units of water ");
        }
        if (weapongathered > 0) {
            successoutputmessage.append(weapongathered).append(" weapons");
        }
        if (foodgathered == 0 && watergathered == 0 && weapongathered == 0) {
            successoutputmessage = new StringBuilder("Your group found nothing this time.");
        }
        else {
            successoutputmessage.append(".");
        }
        dataAccessInterface.changefood(foodgathered);
        dataAccessInterface.changewater(watergathered);
        dataAccessInterface.changeweapon(weapongathered);
        if (isvaildgather()) {
            outputBoundary.prepareSuccessView(new GatherOutputData(successoutputmessage.toString(),
                    "Invalid Gather"));
        }
        else {
            outputBoundary.prepareFailureView("Invalid Gather");
        }
    }

    /**
     * Helper function to determine validity of this gather, right now I can't think of a way of failing.
     * @return vaild gather or not.
     */
    public boolean isvaildgather() {
        return true;
    }
}
