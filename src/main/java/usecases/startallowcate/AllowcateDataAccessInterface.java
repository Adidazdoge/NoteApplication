package usecases.startallowcate;

import entities.PlayerAttributes;

/**
 * Data access interface for allowcating attribute points, assume allowcate a point each time.
 */
public interface AllowcateDataAccessInterface {

    /**
     * We need this to decrease point, and increase according attribute which player selected.
     * @return current Playerattribute class type.
     */
    PlayerAttributes getPlayerAttributes();

    /**
     * Set social to new social value.
     * @param social new social attribute.
     */
    void setSocial(int social);

    /**
     * Set luck to new luck value.
     * @param luck new luck attribute.
     */
    void setLuck(int luck);

    /**
     * Set thrift to new thrift value.
     * @param thrift new thrift attribute.
     */
    void setThrift(int thrift);

    /**
     * Set mobilization to new mobilization value.
     * @param mobilization new mobilization attribute.
     */
    void setMobilization(int mobilization);

    /**
     * Set generalship to new social value.
     * @param generalship new generalship attribute.
     */
    void setGeneralship(int generalship);

    /**
     * Set point to new point value.
     * @param point new point.
     */
    void setPoint(int point);
}
