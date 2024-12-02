package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import entities.*;
import frameworks.database.InMemoryUnifiedDataAccess;
import interface_adapters.NavigationManager;
import interface_adapters.broadcast.BroadcastController;
import interface_adapters.broadcast.BroadcastPresenter;
import interface_adapters.fetchresource.FetchController;
import interface_adapters.fetchresource.FetchPresenter;
import interface_adapters.nevagateallowcatepage.NevagateAllowcateController;
import interface_adapters.nevagateallowcatepage.NevagateAllowcatePresenter;
import interface_adapters.nevagatemainview.NevagateMainController;
import interface_adapters.nevagatemainview.NevagateMainInterface;
import interface_adapters.nevagatemainview.NevagateMainPresenter;
import interface_adapters.startallowcatepoint.AllowcateController;
import interface_adapters.startallowcatepoint.AllowcatePresenter;
import usecases.dailybroadcast.BroadcastInteractor;
import usecases.fetchresource.FetchInteractor;
import usecases.nevagateAllowcatePage.NevagateAllowcateInteractor;
import usecases.nevagatemain.NevagateMainInteractor;
import usecases.startallowcate.AllowcateInteractor;
import frameworks.database.JsonRankingDataAccess;
import view.*;

/**
 * Main of the game applicaition, initialize inmemory database for the game.
 */
public class GameMainApplication {
    /**
     * Ends the game by updating the ranking data for the player.
     * This method writes the player's final game data to the JSON file.
     *
     * @param filePath     The path to the JSON file storing ranking data.
     * @param username     The username of the player.
     * @param score        The final score of the player.
     * @param daysSurvived The number of days the player survived.
     * @param won          Whether the player won the game.
     */
    public static void endGame(String filePath, String username, int score, int daysSurvived, boolean won) {
        try {
            final JsonRankingDataAccess rankingDataAccess = new JsonRankingDataAccess(filePath);
            rankingDataAccess.updateRankingData(username, score, daysSurvived, won);
            // System.out.println("Game results saved to ranking.json!");
        }
        catch (IOException e) {
            System.err.println("Failed to update ranking data: " + e.getMessage());
        }
    }
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
        // Allowcate points ussecase.
        final AllowcatePresenter allowcatePresenter = new AllowcatePresenter(attributeview, navigationManager);
        final AllowcateInteractor allowcateInteractor = new AllowcateInteractor(gamedatabase, allowcatePresenter);
        final AllowcateController allowcateController = new AllowcateController(allowcateInteractor);
        // Nevagate Main usecase.
        final NevagateMainPresenter nevagateMainPresenter = new NevagateMainPresenter(navigationManager);
        final NevagateMainInteractor nevagateMainInteractor =
                new NevagateMainInteractor(gamedatabase, nevagateMainPresenter);
        final NevagateMainController nevagateMainController = new NevagateMainController(nevagateMainInteractor);
        // Fetch Usecase
        final FetchPresenter fetchPresenter = new FetchPresenter(gameView);
        final FetchInteractor fetchInteractor = new FetchInteractor(gamedatabase, fetchPresenter);
        final FetchController fetchController = new FetchController(fetchInteractor);
        // Broadcast Usecase
        final BroadcastPresenter broadcastPresenter = new BroadcastPresenter(gameView);
        final BroadcastInteractor broadcastInteractor = new BroadcastInteractor(gamedatabase, broadcastPresenter);
        final BroadcastController broadcastController = new BroadcastController(broadcastInteractor);
        gameView.setFetchController(fetchController, broadcastController);
        attributeview.setAllowcateController(allowcateController, nevagateMainController);
        // Example of how to use the endGame method
        // endGame("path/to/rankings.json", "Player1", score, daysSurvived, won);
    }
}
