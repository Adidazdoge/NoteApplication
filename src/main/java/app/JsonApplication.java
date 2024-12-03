package app;

import java.io.IOException;

import frameworks.database.JsonLoginDataAccess;
import frameworks.database.JsonSignupDataAccess;
import frameworks.database.JsonRankingDataAccess;
import interface_adapters.NavigationManagerJson;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.rankinglist.RankingController;
import interface_adapters.rankinglist.RankingPresenter;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import usecases.accountlogin.LoginInteractor;
import usecases.accountsignup.SignupInteractor;
import usecases.accountranking.RankingInteractor;
import view.LoginView;
import view.MainView;
import view.RankingView;
import view.SignUpView;

public class JsonApplication {
    private final NavigationManagerJson navigationManagerJson;
    private final LoginController loginController;
    private final SignupController signupController;
    private final RankingController rankingController;

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
        final LoginView loginView = new LoginView();
        final SignUpView signUpView = new SignUpView();
        final RankingView rankingView = new RankingView();
        final MainView mainView = new MainView();

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

        // Start login view
        loginView.render();
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
