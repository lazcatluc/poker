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
	
	private PlayerController cardController;
	
	private Card card1;
	private Card card2;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);
		
		
		card1 = new CardImpl(Rank.ACE,Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE,Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);
		cardController = new PlayerController();
		Table table = new Table();
		table.setDeck(deck);
		cardController.setTable(table);
	}
	
	@Test
	public void getPlayerCardsCallsDeckTwice() {
		cardController.getCards();
		verify(deck,times(2)).drawCard();
	}
	
	@Test
	public void getPlayerCardsReturnsListOfTwoCards() throws Exception {
		List<Card> playerCards = cardController.getCards();
		
		Assertions.assertThat(playerCards).hasSize(2);
	}
	
	@Test
	public void getPlayerCardsReturnDifferentCards() throws Exception {
		List<Card> firstDraw = cardController.getCards();
		List<Card> secondDraw = cardController.getCards();
		
		Assertions.assertThat(firstDraw).isEqualTo(secondDraw);
	}

}
