package usecases.newday;

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

        // Message builder for day summary
        final StringBuilder messageBuilder = new StringBuilder("Another day has passed. Here's what happened:\n");
        boolean success = true;
        String failmessage = "";
        if (newdayDataAccessObject.getPlayerinfo().getDaysSurvived() >= EntityConstants.MAXNUMDAY) {
            success = false;
        }
        // Process resource changes and build the message
        if (success) {
            decrementresource(messageBuilder, thrift, people, temp);
            incrementresouce(messageBuilder, people);
            final NewdayOutputData outputdata = new NewdayOutputData(messageBuilder.toString(), success, failmessage);
            newdayOutputBoundary.prepareSuccessView(outputdata);
        }
        else {
            failmessage = "Day exceeded failure.";
            newdayOutputBoundary.prepareFailureView(failmessage);
        }
    }

    private void incrementresouce(StringBuilder messageBuilder, int people) {
        // food gain
        final double foodscalar = newdayDataAccessObject.getLocation().getfoodresourceavailable();
        final double foodgain = people * EntityConstants.PEOPLEGAINPERFOOD * foodscalar;
        newdayDataAccessObject.changeFood((int) foodgain);
        messageBuilder.append("  - Food gained: ").append((int) foodgain).append(EntityConstants.NEWLINE);

        // water gain
        final double waterscalar = newdayDataAccessObject.getLocation().getwaterresourceavailable();
        final double watergain = people * EntityConstants.PEOPLEGAINPERWATER * waterscalar;
        newdayDataAccessObject.changeWater((int) watergain);
        messageBuilder.append("  - Water gained: ").append((int) watergain).append(EntityConstants.NEWLINE);

        // people gain
        final double peoplegain = people * (EntityConstants.PEOPLEBASEJOINRATE
                * newdayDataAccessObject.getLocation().getpeopleresourceavailable());
        newdayDataAccessObject.changePeople((int) peoplegain);
        messageBuilder.append("  - New members joined: ").append((int) peoplegain).append(EntityConstants.NEWLINE);

        // weaponry gain
        double weapongain = peoplegain + people * EntityConstants.PEOPLEGAINPERWEAPON;
        weapongain = weapongain * newdayDataAccessObject.getLocation().getweaponresourceavailable();
        newdayDataAccessObject.changeWeapon((int) weapongain);
        messageBuilder.append("  - Weaponry gained: ").append((int) weapongain).append(EntityConstants.NEWLINE);
    }

    private void decrementresource(StringBuilder messageBuilder, int thrift, int people, double temp) {
        // food loss
        final double basetemp = EntityConstants.DEFAULTTEMP;
        final double tempdiff = temp - basetemp;
        double foodloss = people * EntityConstants.PEOPLELOSSPERFOOD;
        if (tempdiff < 0) {
            foodloss += Math.abs(tempdiff);
        }
        foodloss = foodloss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeFood((int) foodloss * -1);
        messageBuilder.append("  - Food lost: ").append((int) foodloss).append(EntityConstants.NEWLINE);

        // water loss
        double waterloss = people * EntityConstants.PEOPLELOSSPERWATER;
        if (tempdiff > 0) {
            waterloss += Math.abs(tempdiff);
        }
        waterloss = waterloss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWater((int) waterloss * -1);
        messageBuilder.append("  - Water lost: ").append((int) waterloss).append(EntityConstants.NEWLINE);

        // people loss
        final double peopleloss = people * (EntityConstants.PEOPLEBASEDEATHRATE
                * newdayDataAccessObject.getLocation().getthreatlevel());
        newdayDataAccessObject.changePeople((int) peopleloss * -1);
        messageBuilder.append("  - People lost: ").append((int) peopleloss).append(EntityConstants.NEWLINE);

        // weaponry loss
        final double weaponloss = peopleloss * (1 - thrift * EntityConstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWeapon((int) weaponloss * -1);
        messageBuilder.append("  - Weaponry lost: ").append((int) weaponloss).append(EntityConstants.NEWLINE);
    }

}
