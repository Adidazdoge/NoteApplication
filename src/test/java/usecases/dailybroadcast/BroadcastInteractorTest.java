package usecases.dailybroadcast;

import entities.Inventory;
import entities.Location;
import entities.PlayerAttributes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for BroadcastInteractor.
 */
public class BroadcastInteractorTest {
    private TestBroadcastDataAccess dataAccess;
    private TestBroadcastOutputBoundary outputBoundary;
    private BroadcastInteractor interactor;

    @BeforeEach
    public void setUp() {
        // 初始化模拟的 DataAccess 和 OutputBoundary
        dataAccess = new TestBroadcastDataAccess();
        outputBoundary = new TestBroadcastOutputBoundary();
        interactor = new BroadcastInteractor(dataAccess, outputBoundary);
    }

    @Test
    public void testBroadcastSuccess() {
        // 模拟数据：玩家有足够的行动点
        dataAccess.setActionPoint(1);
        dataAccess.setPlayerAttributes(new PlayerAttributes(0, 2, 0, 0, 0, 0)); // 设置社交属性为 2
        dataAccess.setLocation(new TestLocation(0.5)); // 设置资源可用率为 50%

        // 执行广播操作
        interactor.execute(new BroadcastInputData());

        // 验证结果
        Assertions.assertEquals("Though Broadcast, 4 decided to join your group!", outputBoundary.getSuccessMessage());
        Assertions.assertEquals(0, dataAccess.getActionPoint());
        Assertions.assertEquals(4, dataAccess.getPeopleChange());
    }

    @Test
    public void testBroadcastFailure() {
        dataAccess.setActionPoint(0);

        interactor.execute(new BroadcastInputData());

        Assertions.assertEquals("Cannot move, your people are tired!", outputBoundary.getFailureMessage());
    }

    static class TestBroadcastDataAccess implements BroadcastDataAccessInterface {
        private int actionPoint;
        private PlayerAttributes playerAttributes;
        private Location location;
        private int peopleChange;

        @Override
        public PlayerAttributes getPlayerAttributes() {
            return playerAttributes;
        }

        @Override
        public Inventory getInventory() {
            return null;
        }

        @Override
        public void changePeople(int peopleChange) {
            this.peopleChange = peopleChange;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public int getActionPoint() {
            return actionPoint;
        }

        @Override
        public void setActionPoint(int actionPoint) {
            this.actionPoint = actionPoint;
        }

        public void setPlayerAttributes(PlayerAttributes playerAttributes) {
            this.playerAttributes = playerAttributes;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public int getPeopleChange() {
            return peopleChange;
        }
    }

    static class TestBroadcastOutputBoundary implements BroadcastOutputBoundary {
        private String successMessage;
        private String failureMessage;

        @Override
        public void prepareSuccessView(BroadcastOutputData outputData) {
            this.successMessage = outputData.getResultMessage();
        }

        @Override
        public void prepareFailureView(String errorMessage) {
            this.failureMessage = errorMessage;
        }

        public String getSuccessMessage() {
            return successMessage;
        }

        public String getFailureMessage() {
            return failureMessage;
        }
    }

    class TestLocation implements Location {
        private double peopleResourceAvailable;

        public TestLocation(double peopleResourceAvailable) {
            this.peopleResourceAvailable = peopleResourceAvailable;
        }

        @Override
        public Double getsettemperature(int distance) {
            return null; // Not needed for this test
        }

        @Override
        public Double gettemperature() {
            return null; // Not needed for this test
        }

        @Override
        public Double getsetpeopleresourceavailable(int distance) {
            this.peopleResourceAvailable = distance * 0.1;
            return this.peopleResourceAvailable;
        }

        @Override
        public Double getpeopleresourceavailable() {
            return this.peopleResourceAvailable;
        }

        @Override
        public Double getsetfoodresourceavailable(int distance) {
            return null;
        }

        @Override
        public Double getfoodresourceavailable() {
            return null;
        }

        @Override
        public Double getsetwaterresourceavailable(int distance) {
            return null;
        }

        @Override
        public Double getwaterresourceavailable() {
            return null;
        }

        @Override
        public Double getsetweaponresourceavailable(int distance) {
            return null;
        }

        @Override
        public Double getweaponresourceavailable() {
            return null;
        }

        @Override
        public void decreaseresourceavailable() {
            this.peopleResourceAvailable *= 0.9;
        }

        @Override
        public void decreaserepeopleavailable() {
            this.peopleResourceAvailable *= 0.8;
        }

        @Override
        public Double getsetthreatlevel(int distance) {
            return null;
        }

        @Override
        public Double getthreatlevel() {
            return null;
        }

        @Override
        public String toString() {
            return "TestLocation";
        }

        @Override
        public String getDescription() {
            return "A mock location for testing.";
        }
    }

}
