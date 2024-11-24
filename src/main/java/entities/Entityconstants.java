package entities;

/**
 * Constants used in this program.
 * Notice, by changing these constant, we can alter game dramatically.
 * this could be used to achieve game balance.
 * as well as for difficulty setting implementation.
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
    // map information and calculation scalar settings.
    public static final String ICELAND = "Iceland";
    public static final String DESERT = "Desert";
    public static final String FOREST = "Forest";
    public static final String CITY = "City";
    public static final String PLAIN = "Plain";
    // name we gonna use.
    public static final int BIOMERADIUS = 16;
    // how the radius is gonna be for each biome, used for map generation.
    public static final int FIRSTCHOICE = 1;
    public static final int SECONDCHOICE = 2;
    public static final int THIRDCHOICE = 3;
    public static final int FOURTHCHOICE = 4;
    // how different choices match their represented int.
    public static final double COMMONEVENTBASEPROB = 0.1;
    public static final double RAREEVENTBASEPROB = 0.03;
    // base event probability(probability at default at start)
    public static final int STARTERHORDEMAGNITUDE = 300;
    public static final int STARTERHORDEDURATION = 10;
    // horde starter setting, how in default it should start on
    public static final int MOBILIZATIONIMPACTSPEED = 10;
    // how player attribute mobilization have impact on speed(how many box they move per move)
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    // name we use.
    public static final int UNARMPEOPLEPOWER = 1;
    public static final int ARMEDPEOPLEPOWER = 5;
    // firepower calculation setting.
    public static final int AMBUSHPOWER = 35;
    public static final int AMBUSHNEGOTIATE = 4;
    // for event ambush, what's required firepower to fight back, and required social ability to negotiate etc.
    public static final int AMBUSHFIGHTSUCCESSRESOURCEFOOD = 15;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEWATER = 15;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEPEOPLE = 0;
    public static final int AMBUSHFIGHTSUCCESSRESOURCEWEAPON = 4;
    // for event ambush, resource get for successful fight back.
    public static final int AMBUSHFAILRESOURCEFOOD = -30;
    public static final int AMBUSHFAILRESOURCEWATER = -30;
    public static final int AMBUSHFAILRESOURCEPEOPLE = -5;
    public static final int AMBUSHFAILRESOURCEWEAPON = -10;
    // for event ambush, resource lost for failed.
    public static final int ESCAPEMAXDISTANCE = 10;
    // for any general escape choice, the distance move randomly at max.
    public static final int LENGTHMESSAGECOMMA = 17;


}
