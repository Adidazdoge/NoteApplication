package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import entities.*;
import frameworks.database.InMemoryUnifiedDataAccess;
import interface_adapters.NavigationManager;
import interface_adapters.nevagateallowcatepage.NevagateAllowcateController;
import interface_adapters.nevagateallowcatepage.NevagateAllowcatePresenter;
import interface_adapters.startallowcatepoint.AllowcatePresenter;
import usecases.nevagateAllowcatePage.NevagateAllowcateInteractor;
import view.*;

/**
 * Main of the game applicaition, initialize inmemory database for the game.
 */
public class GameMainApplication {

    /**
     * Main of the game loop.
     * @param args args
     */
    public static void main(String[] args) {
        // initialized map
        final ArrayList<String> environments = new ArrayList<>(Arrays.asList(EntityConstants.PLAIN,
                EntityConstants.CITY, EntityConstants.FOREST, EntityConstants.DESERT, EntityConstants.ICELAND));
        final MapFactory mapfact = new MapFactory();
        final ArrayList<Map<String, ArrayList<Map.Entry<Integer, Integer>>>> cores =
                mapfact.getCores(EntityConstants.MAPWIDTH, EntityConstants.MAPHEIGHT, environments);
        final ArrayList<ArrayList<Location>> grids = mapfact.getGrids(EntityConstants.MAPWIDTH,
                EntityConstants.MAPHEIGHT, cores);
        final Maps gameMap = new Maps(grids);
        // initialize player and their info.
        final PlayerAttributes playerAttributes = new PlayerAttributes();
        final PlayerInfo playerInfo = new PlayerInfo("Currentplayer");
        final PlayerLocation playerLocation = new PlayerLocation();
        final Inventory playerInventory = new Inventory();
        final Location currentlocation = grids.get(playerLocation.getYcoordinate())
                        .get(playerLocation.getXcoordinate());
        // initialize events and horde
        final EventAmbush ambush = new EventAmbush();
        final EventFlood flood = new EventFlood();
        final EventBlizzard blizzard = new EventBlizzard();
        final EventSurvivorJoins survivor = new EventSurvivorJoins();
        final EventTraderEncounter traderEncounter = new EventTraderEncounter();
        final Horde horde = new Horde();
        // initialize ingame in memory database/dataaccess.
        final InMemoryUnifiedDataAccess gamedatabase =
                new InMemoryUnifiedDataAccess(playerAttributes, playerInventory, new ArrayList<>(), currentlocation,
                        horde, playerInfo, playerLocation, gameMap, ambush, blizzard, flood, survivor, traderEncounter);
        // initialize each gameing view.
        final MainView mainView = new MainView();
        final CharacterCreationView attributeview = new CharacterCreationView();
        final GameView gameView = new GameView();
        final EventView eventView = new EventView();
        final GameOverView gameOverView = new GameOverView();
        final InformationView informationView = new InformationView("Daily log");
        // initialize nevagation manager
        final NavigationManager navigationManager =
                new NavigationManager(mainView, attributeview, gameView, eventView, informationView, gameOverView);
        // initialize each usecase.
        // Nevagate to allowcate page usecase.
        final NevagateAllowcatePresenter nevagateallowcatepresener = new NevagateAllowcatePresenter(navigationManager);
        final NevagateAllowcateInteractor nevagateAllowcateInteractor =
                new NevagateAllowcateInteractor(gamedatabase, nevagateallowcatepresener);
        final NevagateAllowcateController nevagateAllowcateController =
                new NevagateAllowcateController(nevagateAllowcateInteractor);
        mainView.setNevagateAllowcateController(nevagateAllowcateController);
        mainView.render();

    }
}
