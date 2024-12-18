package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import entities.*;
import frameworks.database.InMemoryUnifiedDataAccess;
import frameworks.database.JsonLoginDataAccess;
import frameworks.database.JsonSignupDataAccess;
import frameworks.database.JsonRankingDataAccess;
import interface_adapters.EventManager;
import interface_adapters.NavigationManager;
import interface_adapters.NavigationManagerJson;
import interface_adapters.broadcast.BroadcastController;
import interface_adapters.broadcast.BroadcastPresenter;
import interface_adapters.dailygather.DailyGatherController;
import interface_adapters.dailygather.DailyGatherPresenter;
import interface_adapters.dailymove.DailyMoveController;
import interface_adapters.dailymove.DailyMovePresenter;
import interface_adapters.endprocesshorde.HordeController;
import interface_adapters.endprocesshorde.HordePresenter;
import interface_adapters.eventdecide.EventDecideController;
import interface_adapters.eventdecide.EventDecidePresenter;
import interface_adapters.eventinitializer.EventInitializerController;
import interface_adapters.eventinitializer.EventInitializerPresenter;
import interface_adapters.eventrespond.ambush.AmbushResponseController;
import interface_adapters.eventrespond.ambush.AmbushResponsePresenter;
import interface_adapters.eventrespond.blizzard.BlizzardResponseController;
import interface_adapters.eventrespond.blizzard.BlizzardResponsePresenter;
import interface_adapters.eventrespond.flood.FloodResponseController;
import interface_adapters.eventrespond.flood.FloodResponsePresenter;
import interface_adapters.eventrespond.survivor.SurvivorResponseController;
import interface_adapters.eventrespond.survivor.SurvivorResponsePresenter;
import interface_adapters.eventrespond.trader.TraderResponseController;
import interface_adapters.eventrespond.trader.TraderResponsePresenter;
import interface_adapters.fetchcurrentevent.FetchEventController;
import interface_adapters.fetchcurrentevent.FetchEventPresenter;
import interface_adapters.fetchresource.FetchController;
import interface_adapters.fetchresource.FetchPresenter;
import interface_adapters.gamelosedetecter.LoseController;
import interface_adapters.gamelosedetecter.LosePresenter;
import interface_adapters.gameminimap.MinimapController;
import interface_adapters.gameminimap.MinimapPresenter;
import interface_adapters.gamenewday.NewdayController;
import interface_adapters.gamenewday.NewdayPresenter;
import interface_adapters.gameplacedescription.PlaceDescriptionController;
import interface_adapters.gameplacedescription.PlaceDescriptionPresenter;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.nevagateallowcatepage.NevagateAllowcateController;
import interface_adapters.nevagateallowcatepage.NevagateAllowcatePresenter;
import interface_adapters.nevagateevent.NevagateEventController;
import interface_adapters.nevagateevent.NevagateEventPresenter;
import interface_adapters.nevagategame.NevagateGameController;
import interface_adapters.nevagategame.NevagateGamePresenter;
import interface_adapters.nevagategameover.NevagateGameOverController;
import interface_adapters.nevagategameover.NevagateGameOverPresenter;
import interface_adapters.nevagatemainview.NevagateMainController;
import interface_adapters.nevagatemainview.NevagateMainPresenter;
import interface_adapters.rankinglist.RankingController;
import interface_adapters.rankinglist.RankingPresenter;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import interface_adapters.startallowcatepoint.AllowcateController;
import interface_adapters.startallowcatepoint.AllowcatePresenter;
import usecases.accountlogin.LoginInteractor;
import usecases.accountsignup.SignupInteractor;
import usecases.accountranking.RankingInteractor;
import usecases.chatgpt.ChatGptResponseParser;
import usecases.chatgpt.ChatGptService;
import usecases.dailybroadcast.BroadcastInteractor;
import usecases.dailygather.GatherInteractor;
import usecases.dailymove.MoveInteractor;
import usecases.endprocesshorde.HordeInteractor;
import usecases.eventdecide.DecideEventInteractor;
import usecases.eventinitialize.EventInitializeInteractor;
import usecases.eventrespond.ambush.AmbushEventInteractor;
import usecases.eventrespond.blizzard.BlizzardEventInteractor;
import usecases.eventrespond.flood.FloodEventInteractor;
import usecases.eventrespond.survivor.SurvivorEventInteractor;
import usecases.eventrespond.trader.TraderEventInteractor;
import usecases.fetchcurrentevent.CurrentEventInteractor;
import usecases.fetchresource.FetchInteractor;
import usecases.gamelosedetecter.LoseInteractor;
import usecases.gameminimap.MinimapInteractor;
import usecases.gamenewday.NewdayInteractor;
import usecases.gameplacedescription.PlaceDescriptionInteractor;
import usecases.nevagateAllowcatePage.NevagateAllowcateInteractor;
import usecases.nevagateEventPage.NevagateEventInteractor;
import usecases.nevagateGame.NevagateGameInteractor;
import usecases.nevagateGameover.NevagateGameOverInteractor;
import usecases.navigateMain.NavigateMainInteractor;
import usecases.startallowcate.AllowcateInteractor;
import view.*;

public class JsonApplication {
    private final NavigationManagerJson navigationManagerJson;
    private final LoginController loginController;
    private final SignupController signupController;
    private final RankingController rankingController;
    private final LoginView loginView;
    private final SignUpView signUpView;
    private final RankingView rankingView;
    private final MainView mainView;
    private final RestartGameController restartGameController = new RestartGameController(this);

    /**
     * Initializes the integrated application.
     *
     * @param loginFilePath   Path to the login JSON file.
     * @param signupFilePath  Path to the signup JSON file.
     * @param rankingFilePath Path to the ranking JSON file.
     * @throws IOException If there is an issue accessing the JSON files.
     */
    public JsonApplication(String loginFilePath, String signupFilePath, String rankingFilePath) throws IOException {
        // Initialize Views
        this.loginView = new LoginView();
        this.signUpView = new SignUpView();
        this.rankingView = new RankingView();
        this.mainView = new MainView();

        // Navigation Manager
        navigationManagerJson = new NavigationManagerJson(loginView, signUpView, mainView, rankingView);

        // Login Initialization
        final JsonLoginDataAccess loginDataAccess = new JsonLoginDataAccess(loginFilePath);
        final LoginPresenter loginPresenter = new LoginPresenter(loginView, navigationManagerJson);
        final LoginInteractor loginInteractor = new LoginInteractor(loginDataAccess, loginPresenter);
        loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);

        // Signup Initialization
        final JsonSignupDataAccess signupDataAccess = new JsonSignupDataAccess(signupFilePath);
        final SignupPresenter signupPresenter = new SignupPresenter(signUpView);
        final SignupInteractor signupInteractor = new SignupInteractor(signupDataAccess, signupPresenter);
        signupController = new SignupController(signupInteractor);
        signUpView.setSignupController(signupController);

        // Ranking Initialization
        final JsonRankingDataAccess rankingDataAccess = new JsonRankingDataAccess(rankingFilePath);
        final RankingPresenter rankingPresenter = new RankingPresenter(rankingView);
        final RankingInteractor rankingInteractor = new RankingInteractor(rankingDataAccess, rankingPresenter);
        rankingController = new RankingController(rankingInteractor);
        rankingView.setRankingController(rankingController);

        // Link navigation manager to login view
        loginView.setNavigationManager(navigationManagerJson);
        signUpView.setNavigationManager(navigationManagerJson);
        rankingView.setNavigationManager(navigationManagerJson);
        mainView.setNavigationManager(navigationManagerJson);
        mainView.setRestartGameController(this.restartGameController);

        // Start login view
        loginView.render();
    }
    public void startnewgame() {
        gameApplication(mainView, navigationManagerJson);
    }

    private void gameApplication(MainView mainView, NavigationManagerJson navigationManagerJson) {
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
        final NevagateAllowcatePresenter nevagateAllowcatePresenter = new NevagateAllowcatePresenter(navigationManager);
        final NevagateAllowcateInteractor nevagateAllowcateInteractor = new NevagateAllowcateInteractor(
                gamedatabase, nevagateAllowcatePresenter);
        final NevagateAllowcateController nevagateAllowcateController = new NevagateAllowcateController(
                nevagateAllowcateInteractor);

        final ChatGptService chatGptService = new ChatGptService();
        final ChatGptResponseParser responseParser = new ChatGptResponseParser();
        // Allowcate points ussecase.
        final AllowcatePresenter allowcatePresenter = new AllowcatePresenter(attributeview, navigationManager);
        final AllowcateInteractor allowcateInteractor = new AllowcateInteractor(gamedatabase, allowcatePresenter);
        final AllowcateController allowcateController = new AllowcateController(allowcateInteractor);
        // Nevagate Main usecase.
        final NevagateMainPresenter nevagateMainPresenter = new NevagateMainPresenter(navigationManager);
        final NavigateMainInteractor nevagateMainInteractor =
                new NavigateMainInteractor(gamedatabase, nevagateMainPresenter);
        final NevagateMainController nevagateMainController = new NevagateMainController(nevagateMainInteractor);
        // Fetch Usecase
        final FetchPresenter fetchPresenter = new FetchPresenter(gameView);
        final FetchInteractor fetchInteractor = new FetchInteractor(gamedatabase, fetchPresenter);
        final FetchController fetchController = new FetchController(fetchInteractor);
        // Broadcast Usecase
        final BroadcastPresenter broadcastPresenter = new BroadcastPresenter(gameView);
        final BroadcastInteractor broadcastInteractor = new BroadcastInteractor(gamedatabase, broadcastPresenter);
        final BroadcastController broadcastController = new BroadcastController(broadcastInteractor);
        // Place description usecase
        final PlaceDescriptionPresenter placeDescriptionPresenter = new PlaceDescriptionPresenter(gameView);
        final PlaceDescriptionInteractor placeDescriptionInteractor =
                new PlaceDescriptionInteractor(gamedatabase, placeDescriptionPresenter);
        final PlaceDescriptionController placeDescriptionController =
                new PlaceDescriptionController(placeDescriptionInteractor);

        // gather description usecase
        final DailyGatherPresenter dailyGatherPresenter = new DailyGatherPresenter(gameView);
        final GatherInteractor gatherInteractor = new GatherInteractor(gamedatabase, dailyGatherPresenter);
        final DailyGatherController dailyGatherController = new DailyGatherController(gatherInteractor);

        // Move usecase.
        final DailyMovePresenter dailyMovePresenter = new DailyMovePresenter(gameView);
        final MoveInteractor moveInteractor = new MoveInteractor(gamedatabase, dailyMovePresenter);
        final DailyMoveController dailyMoveController = new DailyMoveController(moveInteractor);

        // Nevagate Event usecase
        final NevagateEventPresenter nevagateEventPresenter = new NevagateEventPresenter(navigationManager, gameView);
        final NevagateEventInteractor nevagateEventInteractor =
                new NevagateEventInteractor(gamedatabase, nevagateEventPresenter);
        final NevagateEventController nevagateEventController = new NevagateEventController(nevagateEventInteractor);

        // Event Initialize usecase
        final EventInitializerPresenter eventInitializerPresenter = new EventInitializerPresenter(eventView);
        final EventInitializeInteractor eventInitializeInteractor =
                new EventInitializeInteractor(gamedatabase, eventInitializerPresenter);
        final EventInitializerController eventInitializerController =
                new EventInitializerController(eventInitializeInteractor);

        // Event Decide usecase
        final EventDecidePresenter eventDecidePresenter = new EventDecidePresenter(gameView);
        final DecideEventInteractor decideEventInteractor =
                new DecideEventInteractor(gamedatabase, eventDecidePresenter);
        final EventDecideController eventDecideController = new EventDecideController(decideEventInteractor);

        // Event Respond usecase ambush
        final AmbushResponsePresenter ambushresponsePresenter = new AmbushResponsePresenter(eventView);
        final AmbushEventInteractor ambushEventInteractor =
                new AmbushEventInteractor(gamedatabase, ambushresponsePresenter, chatGptService);
        final AmbushResponseController ambushResponseController = new AmbushResponseController(ambushEventInteractor);

        // Event Respond usecase blizzard
        final BlizzardResponsePresenter blizzardResponsePresenter = new BlizzardResponsePresenter(eventView);
        final BlizzardEventInteractor blizzardEventInteractor =
                new BlizzardEventInteractor(gamedatabase, blizzardResponsePresenter, chatGptService);
        final BlizzardResponseController blizzardResponseController =
                new BlizzardResponseController(blizzardEventInteractor);

        // Event Respond usecase flood
        final FloodResponsePresenter floodResponsePresenter = new FloodResponsePresenter(eventView);
        final FloodEventInteractor floodEventInteractor =
                new FloodEventInteractor(gamedatabase, floodResponsePresenter, chatGptService);
        final FloodResponseController floodResponseController = new FloodResponseController(floodEventInteractor);

        // Event Respond usecase survivor
        final SurvivorResponsePresenter survivorResponsePresenter = new SurvivorResponsePresenter(eventView);
        final SurvivorEventInteractor survivorEventInteractor =
                new SurvivorEventInteractor(gamedatabase, survivorResponsePresenter, chatGptService);
        final SurvivorResponseController survivorResponseController =
                new SurvivorResponseController(survivorEventInteractor);

        // Event Respond usecase trader
        final TraderResponsePresenter traderResponsePresenter = new TraderResponsePresenter(eventView);
        final TraderEventInteractor traderEventInteractor =
                new TraderEventInteractor(gamedatabase, traderResponsePresenter, chatGptService);
        final TraderResponseController traderResponseController = new TraderResponseController(traderEventInteractor);

        // Initialize Event manager
        final EventManager eventManager = new EventManager(ambushResponseController, blizzardResponseController,
                floodResponseController, survivorResponseController, traderResponseController);

        // Fetch Event usecase
        final FetchEventPresenter fetchEventPresenter = new FetchEventPresenter(eventView);
        final CurrentEventInteractor currentEventInteractor =
                new CurrentEventInteractor(gamedatabase, fetchEventPresenter);
        final FetchEventController fetchEventController = new FetchEventController(currentEventInteractor);

        // Nevagate Game usecase
        final NevagateGamePresenter nevagateGamePresenter = new NevagateGamePresenter(navigationManager);
        final NevagateGameInteractor nevagateGameInteractor =
                new NevagateGameInteractor(gamedatabase, nevagateGamePresenter);
        final NevagateGameController nevagateGameController = new NevagateGameController(nevagateGameInteractor);

        // Newday Game usecase
        final NewdayPresenter newdayPresenter = new NewdayPresenter(gameView);
        final NewdayInteractor newdayInteractor = new NewdayInteractor(gamedatabase, newdayPresenter);
        final NewdayController newdayController = new NewdayController(newdayInteractor);

        // Minimap update usecase
        final MinimapPresenter minimapPresenter = new MinimapPresenter(gameView);
        final MinimapInteractor minimapInteractor = new MinimapInteractor(gamedatabase, minimapPresenter);
        final MinimapController minimapController = new MinimapController(minimapInteractor);

        // Lose detect usecase
        final LosePresenter losePresenter = new LosePresenter(gameOverView, navigationManager, gameView);
        final LoseInteractor loseInteractor = new LoseInteractor(gamedatabase, losePresenter);
        final LoseController loseController = new LoseController(loseInteractor);

        // End process horde usecase
        final HordePresenter hordePresenter = new HordePresenter(gameOverView, navigationManager, gameView);
        final HordeInteractor hordeInteractor = new HordeInteractor(gamedatabase, hordePresenter);
        final HordeController hordeController = new HordeController(hordeInteractor);

        // Navigate Game Over view usecase
        final NevagateGameOverPresenter nevagateGameOverPresenter =
                new NevagateGameOverPresenter(navigationManager);
        final NevagateGameOverInteractor nevagateGameOverInteractor =
                new NevagateGameOverInteractor(gamedatabase, nevagateGameOverPresenter);
        final NevagateGameOverController nevagateGameOverController =
                new NevagateGameOverController(nevagateGameOverInteractor);


        // Example of how to use the endGame method
        // endGame("path/to/rankings.json", "Player1", score, daysSurvived, won);
        gameView.setController(fetchController, broadcastController,
                placeDescriptionController, dailyGatherController, dailyMoveController,
                nevagateEventController, eventDecideController, newdayController, minimapController, loseController,
                hordeController, nevagateGameOverController);
        attributeview.setAllowcateController(allowcateController, nevagateMainController);
        eventView.setController(eventInitializerController, fetchEventController, nevagateGameController);
        eventView.setManager(eventManager);
        gameOverView.setController(nevagateMainController);
        gameOverView.setNavigationManager(navigationManagerJson);
        gameOverView.setRestartGameController(this.restartGameController);
        mainView.setNevagateAllowcateController(nevagateAllowcateController);
    }

    /**
     * Entry point to initialize the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            new JsonApplication("PlayerFile", "PlayerFile", "RankingFile");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
