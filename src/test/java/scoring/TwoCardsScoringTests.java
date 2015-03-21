package scoring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import player.Player;
import player.PlayerImpl;
import cards.CardImpl;
import cards.Rank;
import cards.Suit;

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

    @Test
    public void playerWithPairWinsOverLamePlayer() {
        Player player1 = new PlayerImpl("Player1");
        player1.dealCard(new CardImpl(Rank.ACE, Suit.CLUBS));
        player1.dealCard(new CardImpl(Rank.ACE, Suit.DIAMONDS));

        Player player2 = new PlayerImpl("Player2");
        player2.dealCard(new CardImpl(Rank.ACE, Suit.HEARTS));
        player2.dealCard(new CardImpl(Rank.KING, Suit.CLUBS));

        Scoring scoring = new TwoCardsScoring();
        Result result = scoring.getResult(Arrays.asList(player1, player2));
        assertTrue(result.isWinner(player1));
        assertFalse(result.isWinner(player2));
    }
    
    
    @Test
    public void playerWithEqualHandsAreBothWinners() {
        Player player1 = new PlayerImpl("Player1");
        player1.dealCard(new CardImpl(Rank.KING, Suit.SPADES));
        player1.dealCard(new CardImpl(Rank.NINE, Suit.DIAMONDS));

        Player player2 = new PlayerImpl("Player2");
        player2.dealCard(new CardImpl(Rank.NINE, Suit.HEARTS));
        player2.dealCard(new CardImpl(Rank.KING, Suit.DIAMONDS));

        Scoring scoring = new TwoCardsScoring();
        Result result = scoring.getResult(Arrays.asList(player2, player1));
        assertTrue(result.isWinner(player1));
        assertTrue(result.isWinner(player2));
    }
}
