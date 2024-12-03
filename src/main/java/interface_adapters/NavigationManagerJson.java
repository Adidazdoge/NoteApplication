package interface_adapters;

import view.*;

public class NavigationManagerJson {
    private final LoginView loginView;
    private final SignUpView signUpView;
    private final RankingView rankingView;
    private final MainView mainView;

    public NavigationManagerJson(LoginView loginView, SignUpView signUpView, MainView mainView, RankingView rankingView) {
        this.loginView = loginView;
        this.signUpView = signUpView;
        this.mainView = mainView;
        this.rankingView = rankingView;
    }

    public void showLoginView() {
        loginView.render();
        signUpView.disrender();
        mainView.disrender();
        rankingView.disrender();
    }

    public void showSignUpView() {
        loginView.disrender();
        signUpView.render();
        mainView.disrender();
        rankingView.disrender();
    }

    public void showMainView() {
        loginView.disrender();
        signUpView.disrender();
        mainView.render();
        rankingView.disrender();
    }

    public void showRankingView() {
        loginView.disrender();
        signUpView.disrender();
        mainView.disrender();
        rankingView.render();
    }
}
