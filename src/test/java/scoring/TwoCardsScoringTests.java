package scoring;

import org.junit.Test;
import player.Player;
import player.PlayerImpl;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoCardsScoringTests {

    @Test
    public void singlePlayerWins() {
        Player player1 = new PlayerImpl("Player1");
        Player player2 = new PlayerImpl("Player2");
        Scoring scoring = new TwoCardsScoring();
        Result result = scoring.getResult(Arrays.asList(player1));
        assertTrue(result.isWinner(player1));
        assertFalse(result.isWinner(player2));
    }

}
