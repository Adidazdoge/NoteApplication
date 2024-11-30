package interface_adapters.presenters;

import frameworks.view.GameView;
import frameworks.view.MainView;
import frameworks.view.RankingView;
import frameworks.view.LoginView;

/**
 * Main menu presenter.
 * Add liseners to main menu buttons
 */

public class MainMenu {
    private MainView mainView;

    public MainMenu() {
        mainView = new MainView();
        addListeners();
    }

    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    private void addListeners() {

        mainView.getNewGameButton().addActionListener(e -> {
            mainView.dispose();
            new GameView();
        });

        mainView.getRankingButton().addActionListener(e -> {
            mainView.dispose();
            new RankingView();
        });

        mainView.getQuitButton().addActionListener(e -> {
            System.exit(0);
        });

        mainView.getLogoutButton().addActionListener(e -> {
            mainView.dispose();
            new LoginView();
        });
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
