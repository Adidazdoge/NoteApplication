package usecases.newday;

import entities.Entityconstants;

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
        if (newdayDataAccessObject.getPlauerinfo().getDaysSurvived() >= Entityconstants.MAXNUMDAY) {
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
        final double foodgain = people * Entityconstants.PEOPLEGAINPERFOOD * foodscalar;
        newdayDataAccessObject.changeFood((int) foodgain);
        messageBuilder.append("  - Food gained: ").append((int) foodgain).append(Entityconstants.NEWLINE);

        // water gain
        final double waterscalar = newdayDataAccessObject.getLocation().getwaterresourceavailable();
        final double watergain = people * Entityconstants.PEOPLEGAINPERWATER * waterscalar;
        newdayDataAccessObject.changeWater((int) watergain);
        messageBuilder.append("  - Water gained: ").append((int) watergain).append(Entityconstants.NEWLINE);

        // people gain
        final double peoplegain = people * (Entityconstants.PEOPLEBASEJOINRATE
                * newdayDataAccessObject.getLocation().getpeopleresourceavailable());
        newdayDataAccessObject.changePeople((int) peoplegain);
        messageBuilder.append("  - New members joined: ").append((int) peoplegain).append(Entityconstants.NEWLINE);

        // weaponry gain
        double weapongain = peoplegain + people * Entityconstants.PEOPLEGAINPERWEAPON;
        weapongain = weapongain * newdayDataAccessObject.getLocation().getweaponresourceavailable();
        newdayDataAccessObject.changeWeapon((int) weapongain);
        messageBuilder.append("  - Weaponry gained: ").append((int) weapongain).append(Entityconstants.NEWLINE);
    }

    private void decrementresource(StringBuilder messageBuilder, int thrift, int people, double temp) {
        // food loss
        final double basetemp = Entityconstants.DEFAULTTEMP;
        final double tempdiff = temp - basetemp;
        double foodloss = people * Entityconstants.PEOPLELOSSPERFOOD;
        if (tempdiff < 0) {
            foodloss += Math.abs(tempdiff);
        }
        foodloss = foodloss * (1 - thrift * Entityconstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeFood((int) foodloss * -1);
        messageBuilder.append("  - Food lost: ").append((int) foodloss).append(Entityconstants.NEWLINE);

        // water loss
        double waterloss = people * Entityconstants.PEOPLELOSSPERWATER;
        if (tempdiff > 0) {
            waterloss += Math.abs(tempdiff);
        }
        waterloss = waterloss * (1 - thrift * Entityconstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWater((int) waterloss * -1);
        messageBuilder.append("  - Water lost: ").append((int) waterloss).append(Entityconstants.NEWLINE);

        // people loss
        final double peopleloss = people * (Entityconstants.PEOPLEBASEDEATHRATE
                * newdayDataAccessObject.getLocation().getthreatlevel());
        newdayDataAccessObject.changePeople((int) peopleloss * -1);
        messageBuilder.append("  - People lost: ").append((int) peopleloss).append(Entityconstants.NEWLINE);

        // weaponry loss
        final double weaponloss = peopleloss * (1 - thrift * Entityconstants.THRIFTIMPACTRESOURCELOSS);
        newdayDataAccessObject.changeWeapon((int) weaponloss * -1);
        messageBuilder.append("  - Weaponry lost: ").append((int) weaponloss).append(Entityconstants.NEWLINE);
    }

}
