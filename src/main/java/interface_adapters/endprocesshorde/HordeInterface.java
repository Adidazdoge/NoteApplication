package interface_adapters.endprocesshorde;

/**
 * Interface for horde process which is on the game over page.
 */
public interface HordeInterface {

    /**
     * Update the ui with the info provided on the game over game as summary.
     * @param message message of description to what happened.
     * @param score score of player get at the end.
     */
    void updateUiHorde(String message, int score);

    /**
     * If the usecase was failed, explain why is it failed.
     * @param failmessage fail message.
     */
    void failureHorde(String failmessage);
}
