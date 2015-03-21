package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.CardImpl;
import cards.Deck;
import cards.Rank;
import cards.Suit;

public class CardControllerTest {

	private Deck deck;
	
	private CardController cardController;
	
	private Card card1;
	private Card card2;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);
		
		
		card1 = new CardImpl(Rank.ACE,Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE,Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);
		cardController = new CardController();
		cardController.setDeck(deck);
	}
	
	@Test
	public void getPlayerCardsCallsDeckTwice() {
		cardController.getPlayerCards();
		verify(deck,times(2)).drawCard();
	}
	
	@Test
	public void getPlayerCardsReturnsListOfTwoCards() throws Exception {
		List<Card> playerCards = cardController.getPlayerCards();
		
		Assertions.assertThat(playerCards).hasSize(2);
	}
	
	@Test
	public void getPlayerCardsReturnDifferentCards() throws Exception {
		List<Card> firstDraw = cardController.getPlayerCards();
		List<Card> secondDraw = cardController.getPlayerCards();
		
		Assertions.assertThat(firstDraw).isEqualTo(secondDraw);
	}

}
