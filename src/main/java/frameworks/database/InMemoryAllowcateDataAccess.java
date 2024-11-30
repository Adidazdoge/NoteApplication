package frameworks.database;

import entities.PlayerAttributes;
import usecases.startallowcate.AllowcateDataAccessInterface;

/**
 * In-Memory implementation of AllowcateDataAccessInterface.
 */
public class InMemoryAllowcateDataAccess implements AllowcateDataAccessInterface {
    private final PlayerAttributes playerAttributes;

    public InMemoryAllowcateDataAccess(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    @Override
    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    @Override
    public void setSocial(int social) {
        playerAttributes.setSocial(social);
    }

    @Override
    public void setLuck(int luck) {
        playerAttributes.setLuck(luck);
    }

    @Override
    public void setThrift(int thrift) {
        playerAttributes.setThrift(thrift);
    }

    @Override
    public void setMobilization(int mobilization) {
        playerAttributes.setMobilization(mobilization);
    }

    @Override
    public void setGeneralship(int generalship) {
        playerAttributes.setGeneralship(generalship);
    }

    @Override
    public void setPoint(int point) {
        playerAttributes.setPoints(point);
    }
}

