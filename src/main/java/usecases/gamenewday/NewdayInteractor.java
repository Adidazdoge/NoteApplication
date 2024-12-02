package usecases.gamenewday;

import entities.EntityConstants;

/**
 * Newday interactor, where logic happen.
 */
public class NewdayInteractor implements NewdayInputBoundary {
    private final NewdayDataAccessInterface newdayDataAccessObject;
    private final NewdayOutputBoundary newdayOutputBoundary;

    public NewdayInteractor(NewdayDataAccessInterface newdayDataAccessObject,
                            NewdayOutputBoundary newdayOutputBoundary) {
        this.newdayDataAccessObject = newdayDataAccessObject;
        this.newdayOutputBoundary = newdayOutputBoundary;
    }

    @Override
    public void execute(NewdayInputData inputdata) {
        final int thrift = newdayDataAccessObject.getPlayerAttributes().getThrift();
        final int people = newdayDataAccessObject.getInventory().getPeople();
        final double temp = newdayDataAccessObject.getLocation().gettemperature();
        final int score = newdayDataAccessObject.getPlayerInfo().getScore();

        // Message builder for day summary
        final StringBuilder messageBuilder = new StringBuilder("Another day has passed. Here's what happened:\n");
        boolean success = true;
        String failMessage = "";

        // 1. Fail if there are unfinished events
        if (!newdayDataAccessObject.getUnprocessedEvents().isEmpty()) {
            success = false;
            failMessage = "There are unfinished events. You must address them before starting a new day.";
        }
        // 2. Fail if the maximum day has been reached or exceeded
        else if (newdayDataAccessObject.getPlayerInfo().getDaysSurvived() >= EntityConstants.MAXNUMDAY) {
            success = false;
            failMessage = "You have reached the maximum number of days. The game is over.";
        }

        if (success) {
            // 3. If days survived is less than 59, apply resource gain/loss logic
            if (newdayDataAccessObject.getPlayerInfo().getDaysSurvived() < EntityConstants.MAXNUMDAY - 1) {
                applyResourceChanges(messageBuilder, thrift, people, temp, score);
            }
            // 4. If it's day 59, display the horde description logic
            else {
                final double temperature = newdayDataAccessObject.getLocation().gettemperature();
                final double threat = newdayDataAccessObject.getLocation().getthreatlevel();
                messageBuilder.append(newdayDataAccessObject.getHorde().getDescription(threat, temperature));
            }

            // Update state and prepare success output
            final NewdayOutputData outputData = new NewdayOutputData(messageBuilder.toString(), true, "");
            newdayOutputBoundary.prepareSuccessView(outputData);

            // Update player state for the new day
            newdayDataAccessObject.setActionPoint(EntityConstants.STARTERACTIONPOINT);
            newdayDataAccessObject.setDaysSurvived(newdayDataAccessObject.getPlayerInfo().getDaysSurvived() + 1);
        }
        else {
            // Prepare failure output
            newdayOutputBoundary.prepareFailureView(failMessage);
        }
    }

    private void applyResourceChanges(StringBuilder messageBuilder, int thrift, int people, double temp, int score) {
        incrementResources(messageBuilder, people, score);
        decrementResources(messageBuilder, thrift, people, temp);
    }

    private void incrementResources(StringBuilder messageBuilder, int people, int score) {
        // Food gain
        final double foodScalar = newdayDataAccessObject.getLocation().getfoodresourceavailable();
        final double foodGain = people * EntityConstants.PEOPLEGAINPERFOOD * foodScalar;
        newdayDataAccessObject.changeFood((int) foodGain);
        messageBuilder.append("  - Food gained: ").append((int) foodGain).append(EntityConstants.NEWLINE);

        // Water gain
        final double waterScalar = newdayDataAccessObject.getLocation().getwaterresourceavailable();
        final double waterGain = people * EntityConstants.PEOPLEGAINPERWATER * waterScalar;
        newdayDataAccessObject.changeWater((int) waterGain);
        messageBuilder.append("  - Water gained: ").append((int) waterGain).append(EntityConstants.NEWLINE);

        int newScore = score + (int) foodGain + (int) waterGain;

        // People gain
        final double peopleGain = people * (EntityConstants.PEOPLEBASEJOINRATE
                * newdayDataAccessObject.getLocation().getpeopleresourceavailable());
        newdayDataAccessObject.changePeople((int) peopleGain);
        messageBuilder.append("  - New members joined: ").append((int) peopleGain).append(EntityConstants.NEWLINE);
        newScore += (int) peopleGain;

        // Weaponry gain
        double weaponGain = peopleGain + people * EntityConstants.PEOPLEGAINPERWEAPON;
        weaponGain = weaponGain * newdayDataAccessObject.getLocation().getweaponresourceavailable();
        newdayDataAccessObject.changeWeapon((int) weaponGain);
        messageBuilder.append("  - Weaponry gained: ").append((int) weaponGain).append(EntityConstants.NEWLINE);

        newScore += (int) weaponGain + EntityConstants.NEWDAYSCORE;

        newdayDataAccessObject.setScore(newScore);
    }

    private void decrementResources(StringBuilder messageBuilder, int thrift, int people, double temp) {
        // Food loss
        final double baseTemp = EntityConstants.DEFAULTTEMP;
        final double tempDiff = temp - baseTemp;
        double foodLoss = people * EntityConstants.PEOPLELOSSPERFOOD;
        if (tempDiff < 0) {
            foodLoss += Math.abs(tempDiff);
        }
        foodLoss = foodLoss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeFood((int) foodLoss * -1);
        messageBuilder.append("  - Food lost: ").append((int) foodLoss).append(EntityConstants.NEWLINE);

        // Water loss
        double waterLoss = people * EntityConstants.PEOPLELOSSPERWATER;
        if (tempDiff > 0) {
            waterLoss += Math.abs(tempDiff);
        }
        waterLoss = waterLoss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWater((int) waterLoss * -1);
        messageBuilder.append("  - Water lost: ").append((int) waterLoss).append(EntityConstants.NEWLINE);

        // People loss
        final double peopleLoss = people * (EntityConstants.PEOPLEBASEDEATHRATE
                * newdayDataAccessObject.getLocation().getthreatlevel());
        newdayDataAccessObject.changePeople((int) peopleLoss * -1);
        messageBuilder.append("  - People lost: ").append((int) peopleLoss).append(EntityConstants.NEWLINE);

        // Weaponry loss
        final double weaponLoss = peopleLoss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWeapon((int) weaponLoss * -1);
        messageBuilder.append("  - Weaponry lost: ").append((int) weaponLoss).append(EntityConstants.NEWLINE);
    }
}
