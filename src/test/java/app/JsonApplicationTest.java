package app;

import frameworks.database.JsonRankingDataAccess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class JsonApplicationTest {

    @Test
    public void testEndGameUpdatesRankingFile() {
        String filePath = "test_ranking.json";
        String username = "Player1";
        int score = 1000;
        int daysSurvived = 10;
        boolean won = true;

        // Call endGame
        JsonApplication.endGame(filePath, username, score, daysSurvived, won);

        // Verify the data in JsonRankingDataAccess
        try {
            JsonRankingDataAccess rankingDataAccess = new JsonRankingDataAccess(filePath);
            assertTrue(rankingDataAccess.getLeaderboard().stream()
                    .anyMatch(entry -> entry.getName().equals(username)
                            && entry.getScore() == score
                            && entry.getDaysSurvived() == daysSurvived
                            && entry.isWon() == won));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
