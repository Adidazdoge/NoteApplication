package app;

public class RestartGameController {

    private final JsonApplication jsonApplication;

    public RestartGameController(JsonApplication jsonApplication) {
        this.jsonApplication = jsonApplication;
    }

    public void resetGame() {
        jsonApplication.startnewgame();
    }
}
