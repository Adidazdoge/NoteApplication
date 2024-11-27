package entities;

/**
 * Constants used in this program.
 * Notice, by changing these constant, we can alter game dramatically.
 * This could be used to achieve game balance as well as for difficulty setting implementation.
 */
public class Entityconstants {
    public static final int STARTERFOOD = 100;
    public static final int STARTERWATER = 100;
    public static final int STARTWEAPON = 5;
    public static final int STARTERPEOPLE = 10;
    public static final int STARTERATRIBUTEPOINT = 20;
    public static final double STARTERRESOURCESCALAR = 1;
    public static final double RESOUCEDECREASERATIO = 0.7;
    public static final double DEFAULTTEMP = 25;
    public static final int CORERANGE = 16;
    public static final double DEFAULTTHREAT = 1;
    public static final double MAXTEMPDIFF = 50;
    public static final double MAXTHREATDIFF = 0.5;

    // Map information and calculation scalar settings.
    public static final String ICELAND = "Iceland";
    public static final String DESERT = "Desert";
    public static final String FOREST = "Forest";
    public static final String CITY = "City";
    public static final String PLAIN = "Plain";

    // Name we gonna use.
    public static final int BIOMERADIUS = 16;
    public static final int FIRSTCHOICE = 1;
    public static final int SECONDCHOICE = 2;
    public static final int THIRDCHOICE = 3;
    public static final int FOURTHCHOICE = 4;

    // Base event probability (probability at default at start).
    public static final double COMMONEVENTBASEPROB = 0.1;
    public static final double RAREEVENTBASEPROB = 0.03;

    // Horde starter setting.
    public static final int STARTERHORDEMAGNITUDE = 300;
    public static final int STARTERHORDEDURATION = 10;

    // Player attribute impacts.
    public static final int MOBILIZATIONIMPACTSPEED = 10;

    // Directions.
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    // Firepower calculation settings.
    public static final int UNARMPEOPLEPOWER = 1;
    public static final int ARMEDPEOPLEPOWER = 5;

    // Ambush constants.
    public static final int AMBUSHPOWER = 35;
    public static final int AMBUSHNEGOTIATE = 4;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEFOOD = 15;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEWATER = 15;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEPEOPLE = 0;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEWEAPON = 4;
    public static final int AMBUSHFAILRESOURCEFOOD = -30;
    public static final int AMBUSHFAILRESOURCEWATER = -30;
    public static final int AMBUSHFAILRESOURCEPEOPLE = -5;
    public static final int AMBUSHFAILRESOURCEWEAPON = -10;

    // Flood constants.
    public static final int FLOODRESOURCELOSS = -10;
    public static final int FLOODSECURELOSS = -5;
    public static final int FLOODDOINGNOTHINGLOSS = -20;

    // Blizzard constants.
    public static final int BLIZZARDRESOURCELOSSFOOD = -15;
    public static final int BLIZZARDRESOURCELOSSWATER = -15;

    // Add these constants under the event-specific section
    // TraderEncounter thresholds
    public static final int TRADERNEGOTIATE = 5; // Minimum social attribute for successful trading
    public static final int TRADERROBBERYPOWER = 30; // Minimum firepower for successful robbery

    // TraderEncounter resource changes
    public static final int TRADERTRADEGAINFOOD = 15; // Resources gained from successful trade
    public static final int TRADERTRADEGAINWATER = 10;
    public static final int TRADERTRADEFAILLOSSFOOD = -5; // Resources lost in trade scam
    public static final int TRADERTRADEFAILLOSSWATER = -3;

    public static final int TRADERROBBERYGAINFOOD = 20; // Resources gained from successful robbery
    public static final int TRADERROBBERYGAINSUPPLIES = 5;
    public static final int TRADERROBBERYFAILLOSSFOOD = -10; // Resources lost in failed robbery
    public static final int TRADERROBBERYFAILLOSSPEOPLE = -2;

    public static final int TRADERIGNORELOSS = 0; // No resource changes for ignoring

}
