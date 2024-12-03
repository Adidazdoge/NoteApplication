package app;

import entities.PlayerInfo;

import static app.JsonApplication.endGame;

public class RestartGameController {

    private final JsonApplication jsonApplication;
    private PlayerInfo playerInfo;

    public RestartGameController(JsonApplication jsonApplication) {
        this.jsonApplication = jsonApplication;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void resetGame() {
        jsonApplication.startnewgame();
    }

    public void saveRecord() {
        endGame("RankingFile", playerInfo.getName(), playerInfo.getScore(),
                playerInfo.getDaysSurvived(), playerInfo.isWon());
    }
}
