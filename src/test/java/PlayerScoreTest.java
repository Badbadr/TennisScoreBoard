import org.example.model.redis.PlayerScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerScoreTest {
    PlayerScore playerScore;
    PlayerScore playerScore2;

    @BeforeEach
    void setUp() {
        playerScore = new PlayerScore();
        playerScore2 = new PlayerScore();
    }
    @Test
    void increaseScore_allPossiblePointsTest() {
        Assertions.assertEquals("0", playerScore.getPoints());
        playerScore.increaseScore(playerScore2);
        playerScore2.increaseScore(playerScore);
        Assertions.assertEquals("15", playerScore.getPoints());
        playerScore.increaseScore(playerScore2);
        playerScore2.increaseScore(playerScore);
        Assertions.assertEquals("30", playerScore.getPoints());
        playerScore.increaseScore(playerScore2);
        playerScore2.increaseScore(playerScore);
        Assertions.assertEquals("40", playerScore.getPoints());
        playerScore.increaseScore(playerScore2);
        Assertions.assertEquals("Ad", playerScore.getPoints());
    }

    @Test
    void increaseGame_OnlyOnePlayerScores() {
        for (int i = 0; i < 4; i++) {
            playerScore.increaseScore(playerScore2);
        }
        Assertions.assertFalse(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore.getSets());
        Assertions.assertEquals(1, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertFalse(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(0, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());
    }

    @Test
    void increaseSet_OnlyOnePlayerScores() {
        for (int i = 0; i < 24; i++) {
            playerScore.increaseScore(playerScore2);
        }
        Assertions.assertFalse(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(1, playerScore.getSets());
        Assertions.assertEquals(0, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertFalse(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(0, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());
    }

    @Test
    void fullGame_OnlyOnePlayerScores() {
        for (int i = 0; i < 48; i++) {
            playerScore.increaseScore(playerScore2);
        }
        Assertions.assertTrue(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(2, playerScore.getSets());
        Assertions.assertEquals(0, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertTrue(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(0, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());

        playerScore.increaseScore(playerScore2);

        Assertions.assertTrue(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(2, playerScore.getSets());
        Assertions.assertEquals(0, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertTrue(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(0, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());
    }

    @Test
    void advantageDisadvantage_test() {
        for (int i = 0; i < 4; i++) {
            playerScore.increaseScore(playerScore2);
            playerScore2.increaseScore(playerScore);
        }

        Assertions.assertEquals("40", playerScore.getPoints());
        Assertions.assertEquals("40", playerScore2.getPoints());

        playerScore.increaseScore(playerScore2);

        Assertions.assertEquals("Ad", playerScore.getPoints());
        Assertions.assertEquals("40", playerScore2.getPoints());

        playerScore2.increaseScore(playerScore);

        Assertions.assertEquals("40", playerScore.getPoints());
        Assertions.assertEquals("40", playerScore2.getPoints());

        playerScore2.increaseScore(playerScore);
        Assertions.assertEquals("Ad", playerScore2.getPoints());

        playerScore2.increaseScore(playerScore);

        Assertions.assertFalse(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore.getSets());
        Assertions.assertEquals(0, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertFalse(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(1, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());
    }

    @Test
    void tiebreak_test() {
        playerScore.setGames(6);
        playerScore2.setGames(5);
        playerScore.setCurrentPointPtr(0);
        playerScore2.setCurrentPointPtr(3);

        playerScore2.increaseScore(playerScore);

        Assertions.assertTrue(playerScore.isTiebreak() && playerScore2.isTiebreak());

        for (int i = 0; i < 6; i++) {
            playerScore.increaseScore(playerScore2);
            playerScore2.increaseScore(playerScore);
        }

        Assertions.assertFalse(playerScore.isFinished());
        Assertions.assertTrue(playerScore.isTiebreak());
        Assertions.assertEquals(6, playerScore.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore.getSets());
        Assertions.assertEquals(6, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertFalse(playerScore2.isFinished());
        Assertions.assertTrue(playerScore2.isTiebreak());
        Assertions.assertEquals(6, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(6, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());

        playerScore.increaseScore(playerScore2);
        playerScore.increaseScore(playerScore2);

        Assertions.assertFalse(playerScore.isFinished());
        Assertions.assertFalse(playerScore.isTiebreak());
        Assertions.assertEquals(0, playerScore.getTiebreakPoints());
        Assertions.assertEquals(1, playerScore.getSets());
        Assertions.assertEquals(0, playerScore.getGames());
        Assertions.assertEquals("0", playerScore.getPoints());

        Assertions.assertFalse(playerScore2.isFinished());
        Assertions.assertFalse(playerScore2.isTiebreak());
        Assertions.assertEquals(0, playerScore2.getTiebreakPoints());
        Assertions.assertEquals(0, playerScore2.getSets());
        Assertions.assertEquals(0, playerScore2.getGames());
        Assertions.assertEquals("0", playerScore2.getPoints());


    }
}
