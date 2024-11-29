package usecases;

import entities.PlayerAttributes;
import org.junit.Test;
import usecases.startallowcate.*;

import static org.junit.Assert.*;

public class AllowcateInteractorTest {

    @Test
    public void testExecuteSuccess() {
        // Initialize PlayerAttributes
        PlayerAttributes playerAttributes = new PlayerAttributes();

        // Create mock DAO
        AllowcateDataAccessInterface mockDAO = new AllowcateDataAccessInterface() {
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
        };

        // Create presenter
        AllowcateOutputBoundary presenter = new AllowcateOutputBoundary() {
            @Override
            public void preparesuccessview(AllowcateOutputData outputData) {
                assertEquals(1, (int) playerAttributes.getThrift());
                assertEquals(19, (int) outputData.getPoint());
            }

            @Override
            public void preparefailureview(String failmessage) {
                fail("Failure is not expected in this test.");
            }
        };

        // Ensure mockDAO and presenter are not null
        assertNotNull(mockDAO);
        assertNotNull(presenter);

        // Create interactor and execute
        AllowcateInteractor interactor = new AllowcateInteractor(mockDAO, presenter);
        interactor.execute(new AllowcateInputdata("Thrift"));
    }


    @Test
    public void testExecuteFailure() {
        PlayerAttributes playerAttributes = new PlayerAttributes();
        playerAttributes.setPoints(0);
        AllowcateInputdata inputdata = new AllowcateInputdata("Social");
        AllowcateOutputBoundary presenter = new AllowcateOutputBoundary() {
            @Override
            public void preparesuccessview(AllowcateOutputData outputData) {
            }

            @Override
            public void preparefailureview(String failmessage) {
                assertEquals(failmessage, "Not enough Attribute points.");
            }
        };

        AllowcateDataAccessInterface mockDAO = new AllowcateDataAccessInterface() {
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
        };
        AllowcateInteractor interactor = new AllowcateInteractor(mockDAO, presenter);
        interactor.execute(inputdata);

    }
}