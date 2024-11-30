package interface_adapters.dailygather;

/**
 * Interface of daily gather, suppose to be implement by the view.
 */
public interface DailyGatherInterface {

    /**
     * All the stuff we have to display and update at this point. Ne wmessage, and updated resource.
     * @param message message for the gather.
     */
    void updateUiGather(String message);

    /**
     * No need to update resource, or anything except the error message telling play why this is invaild move.
     * @param message errormessage.
     */
    void failureGather(String message);
}
