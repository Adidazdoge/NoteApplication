package entities;

/**
 * Constants used in this program.
 * Notice, by changing these constant, we can alter game dramatically.
 * This could be used to achieve game balance as well as for difficulty setting implementation.
 */
public class EntityConstants {
    public static final int NEWDAYSCORE = 100;
    public static final int STARTERFOOD = 100;
    public static final int STARTERWATER = 100;
    public static final int STARTWEAPON = 5;
    public static final int STARTERPEOPLE = 10;
    public static final int STARTERACTIONPOINT = 3;
    public static final int BROADCASTGAIN = 7;
    public static final double SOCIALIMPACTBROADCAST = 0.05;
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
    public static final int ICELANDCOLD = 0;
    public static final int ICELANDEXTREMECOLD = -20;
    public static final String DESERT = "Desert";
    public static final int DESERTHOTDEGREE = 35;
    public static final int DESERTUNBAREABLEDEGREE = 65;
    public static final String FOREST = "Forest";
    public static final double FORESTRICH = 1.25;
    public static final double FORESTEXTREMERICH = 1.75;
    public static final String CITY = "City";
    public static final double CITYDANGER = 1.25;
    public static final double CITYEXTREMEDANGER = 1.75;
    public static final String PLAIN = "Plain";

    // Name we gonna use.
    public static final String SOCIAL = "Social";
    public static final String LUCK = "Luck";
    public static final String MOBILIZATION = "Mobilization";
    public static final String THRIFT = "Thrift";
    public static final String GENERALSHIP = "GeneralShip";

    public static final int MAPHEIGHT = 100;
    public static final int MAPWIDTH = 100;
    public static final int SPAWNXCOOR = MAPWIDTH / 2;
    public static final int SPAWNYCOOR = MAPHEIGHT / 2;
    public static final int BIOMERADIUS = 16;
    public static final int MINIMAPRADIUS = 4;
    public static final int FIRSTCHOICE = 1;
    public static final int SECONDCHOICE = 2;
    public static final int THIRDCHOICE = 3;
    public static final int FOURTHCHOICE = 4;

    // Base event probability (probability at default at start).
    public static final double COMMONEVENTBASEPROB = 0.1;
    public static final double RAREEVENTBASEPROB = 0.03;

    // Horde starter setting.
    public static final int STARTERHORDEMAGNITUDE = 3000;
    public static final int HORDEMAGNITUDELARGE = 4000;
    public static final int HORDEMAGNITUDEEXTREME = 5000;
    public static final int STARTERHORDEDURATION = 10;
    public static final int HORDEDURATIONLONG = 8;
    public static final int HORDEDURATIONSHORT = 6;
    public static final double TEMPIMPACTHORDE = 0.5;

    // Player attribute impacts.
    public static final int MOBILIZATIONIMPACTSPEED = 10;
    public static final double THRIFTIMPACTRESOURCELOSS = 0.03;

    // Newday resource loss module
    public static final double PEOPLELOSSPERFOOD = 1;
    public static final double PEOPLELOSSPERWATER = 1;
    public static final double PEOPLEBASEDEATHRATE = 0.05;

    // Newday resource gain module
    public static final double PEOPLEGAINPERFOOD = 0.9;
    public static final double PEOPLEGAINPERWEAPON = 0.04;
    public static final double PEOPLEGAINPERWATER = 0.9;
    public static final double PEOPLEBASEJOINRATE = 0.04;
    public static final double WEAPONBASELOSERATE = 0.04;

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
    // for event ambush, resource lost for failed.
    // for any general escape choice, the distance move randomly at max.
    public static final int LENGTHMESSAGECOMMA = 17;

    // Flood event constants
    public static final int FLOODRESOURCELOSSFOOD = -10; 
    public static final int FLOODRESOURCELOSSSECURE = -15; 
    public static final int FLOODRESOURCELOSSHIGH = -20; 

    public static final int FLOODPEOPLELOSSLOW = -1; 
    public static final int FLOODPEOPLELOSSMODERATE = -3; 
    public static final int FLOODPEOPLELOSSHIGH = -5;

    // Blizzard constants.
    public static final int BLIZZARDRESOURCELOSSFOOD = -15;
    public static final int BLIZZARDRESOURCELOSSWATER = -15;

    // Add these constants under the event-specific section
    // TraderEncounter thresholds
    public static final int TRADERNEGOTIATE = 5;
    public static final int TRADERROBBERYPOWER = 30;

    // TraderEncounter resource changes
    public static final int TRADERTRADEGAINFOOD = 15;
    public static final int TRADERTRADEGAINWATER = 10;
    public static final int TRADERTRADEFAILLOSSFOOD = -5;
    public static final int TRADERTRADEFAILLOSSWATER = -3;

    public static final int TRADERROBBERYGAINFOOD = 20;
    public static final int TRADERROBBERYGAINSUPPLIES = 5;
    public static final int TRADERROBBERYFAILLOSSFOOD = -10;
    public static final int TRADERROBBERYFAILLOSSPEOPLE = -2;

    public static final int TRADERIGNORELOSS = 0;
    public static final int MAXNUMDAY = 60;

    // Survivor constants
    public static final int SURVIVORACCEPTPEOPLEGAIN = 5; 
    public static final int SURVIVORROBBERYPOWER = 20; 
    public static final int SURVIVORROBBERYGAINFOOD = 10; 
    public static final int SURVIVORROBBERYGAINSUPPLIES = 5;
    public static final int SURVIVORROBBERYFAILLOSSFOOD = -10; 
    public static final int SURVIVORROBBERYFAILLOSSPEOPLE = -2;

    public static final String NEWLINE = "\n";
}
