package controller;

import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.CardImpl;
import cards.Deck;
import cards.Rank;
import cards.Suit;
import player.Player;

public class CardControllerTest {

	private Deck deck;
	
	private PlayerController playerController;
	
	private Card card1;
	private Card card2;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);

        card1 = new CardImpl(Rank.ACE,Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE,Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);

        playerController = new PlayerController();
        playerController.setName("testName");
        playerController.createPlayer();

        Table table = new Table();
		table.setDeck(deck);
		playerController.setTable(table);
	}
	
	@Test
	public void getPlayerCardsCallsDeckTwice() {
		playerController.getCards();
		verify(deck,times(2)).drawCard();
	}
	
	@Test
	public void getPlayerCardsReturnsListOfTwoCards() throws Exception {
		List<Card> playerCards = playerController.getCards();
		
		Assertions.assertThat(playerCards).hasSize(2);
	}
	
	@Test
	public void getPlayerCardsReturnDifferentCards() throws Exception {
		List<Card> firstDraw = playerController.getCards();
		List<Card> secondDraw = playerController.getCards();
		
		Assertions.assertThat(firstDraw).isEqualTo(secondDraw);
	}

    @Test
    public void shouldNotCreatePlayerTheSecondTime() {
        playerController.setName("testName");
        playerController.createPlayer();
        Player player1 = playerController.getPlayer();

        playerController.createPlayer();
        Player player2 = playerController.getPlayer();

        Assert.assertTrue(player1 == player2);
    }

}
