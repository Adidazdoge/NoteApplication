package interface_adapters.dailymove;

import java.util.ArrayList;

/**
 * Move interface usecase for communicate with view in not direct or hard dependent way.
 */
public interface DailyMoveInterface {

    /**
     * Update the minimap after move use case with the nested arraylist of single letter string.
     * @param message description telling you moved.
     */
    void updateUiMove(String message);

    /**
     * Don;t update or change anything if usecase failed, display the error message to player only.
     * @param errormessage Error message.
     */
    void failureMove(String errormessage);
}
