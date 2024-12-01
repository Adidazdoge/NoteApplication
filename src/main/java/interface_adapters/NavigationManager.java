package interface_adapters;

import view.*;

public class NavigationManager {
    private final MainView mainView;
    private final CharacterCreationView characterCreationView;
    private final GameView gameView;
    private final EventView eventView;
    private final InformationView informationView;
    private final GameOverView gameOverView;

    public NavigationManager(MainView mainView,
                             CharacterCreationView characterCreationView, GameView gameView, EventView eventView,
                             InformationView informationView, GameOverView gameOverView) {
        this.mainView = mainView;
        this.characterCreationView = characterCreationView;
        this.gameView = gameView;
        this.eventView = eventView;
        this.informationView = informationView;
        this.gameOverView = gameOverView;
    }

    public void showMainView() {
        mainView.render();
        characterCreationView.disrender();
        gameView.disrender();
        eventView.disrender();
        informationView.disrender();
        gameOverView.disrender();
    }

    public void showCharacterCreationView() {
        mainView.disrender();
        characterCreationView.render();
        gameView.disrender();
        eventView.disrender();
        informationView.disrender();
        gameOverView.disrender();
    }

    public void showEventView() {
        mainView.disrender();
        characterCreationView.disrender();
        gameView.disrender();
        eventView.render();
        informationView.disrender();
        gameOverView.disrender();
    }

    public void showGameView() {
        mainView.disrender();
        characterCreationView.disrender();
        gameView.render();
        eventView.disrender();
        informationView.disrender();
        gameOverView.disrender();
    }

    public void showInformationView() {
        mainView.disrender();
        characterCreationView.disrender();
        gameView.disrender();
        eventView.disrender();
        informationView.render();
        gameOverView.disrender();
    }

    public void showGameOverView() {
        mainView.disrender();
        characterCreationView.disrender();
        gameView.disrender();
        eventView.disrender();
        informationView.disrender();
        gameOverView.render();
    }

}
