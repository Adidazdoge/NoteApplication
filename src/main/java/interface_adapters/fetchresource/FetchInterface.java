package interface_adapters.fetchresource;

/**
 * Fetch interface.
 */
public interface FetchInterface {

    /**
     * Update the resouce appearing on the UI.
     * @param food food
     * @param water water
     * @param people people
     * @param weapon weapon
     */
    void updateUiResource(int food, int water, int people, int weapon);
}