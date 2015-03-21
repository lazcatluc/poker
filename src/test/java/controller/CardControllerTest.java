package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.Deck;

public class CardControllerTest {

	private Deck deck;
	
	private CardController cardController;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);
		
		
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
		
		assertEquals(2,playerCards.size());
	}
	
	@Test
	public void getPlayerCardsReturnDifferentListsOfCards() throws Exception {
		
	}

}
