package interface_adapters.dailygather;

/**
 * Interface of daily gather, suppose to be implement by the view.
 */
public interface DailyGatherInterface {

    /**
     * All the stuff we have to display and update at this point. Ne wmessage, and updated resource.
     * @param message message for the gather.
     * @param food new food.
     * @param water new water amount.
     * @param weapon new weapon amount.
     */
    void updateUi(String message, int food, int water, int weapon);

    /**
     * No need to update resource, or anything except the error message telling play why this is invaild move.
     * @param message errormessage.
     */
    void failure(String message);
}
