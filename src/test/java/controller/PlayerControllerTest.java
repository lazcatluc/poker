package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import game.Bets;
import game.GameBuilderImpl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import player.Player;
import player.PlayerValidatorImpl;
import scoring.TwoCardsScoring;
import cards.Card;
import cards.CardImpl;
import cards.Deck;
import cards.Rank;
import cards.Suit;

public class PlayerControllerTest {

	private Deck deck;

	private PlayerController playerController;

	private Card card1;
	private Card card2;

	private Table table;

	@Before
	public void setup() {
		deck = mock(Deck.class);

		card1 = new CardImpl(Rank.ACE, Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE, Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);

		playerController = new PlayerController();

		table = new Table();
		table.setValidator(new PlayerValidatorImpl());
		table.setDeck(deck);
		table.setGameBuilder(new GameBuilderImpl());
		table.setBets(new Bets());
		playerController.setTable(table);

		playerController.setName("testName");
		playerController.createPlayer();

	}

	@Test
	public void getPlayerCardsCallsDeckTwice() {
		playerController.getCards();
		verify(deck, times(2)).drawCard();
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

		assertTrue(player1 == player2);
	}

	@Test
	public void crashPlayerValidator() {
		PlayerController playerController = new PlayerController();
		playerController.setName("testName");
		playerController.setTable(table);

		//System.out.println("error:" + playerController.getError());

		assertNull(playerController.getError());
		playerController.createPlayer();
		assertNotNull(playerController.getError());

		//System.out.println("error:" + playerController.getError());

	}

	@Test
	public void whenPlayerFoldsTableIsEmpty() throws Exception {

		playerController.fold();

		int numberOfPlayers = table.getNumberOfPlayers();

		assertEquals(0, numberOfPlayers);
	}

	@Test
	public void playerIsOwner() throws Exception {
		Player player = playerController.getPlayer();

		assertTrue(table.isOwner(player));
	}

	@Test
	public void firstPlayerIsOwner() throws Exception {
		Player firstPlayer = playerController.getPlayer();

		assertTrue(table.isOwner(firstPlayer));
	}

	@Test
	public void whenFirstPlayerFoldsSecondPlayerBecomesOwner() throws Exception {
		PlayerController pc2 = new PlayerController();
		pc2.setTable(table);

		pc2.setName("Player2");
		pc2.createPlayer();

		
		playerController.fold();

		Player secondPlayer = pc2.getPlayer();

		assertTrue(table.isOwner(secondPlayer));
	}
	
	@Test
	public void checkTablePotMatchesPlayersBet() throws Exception {

		PlayerController playerController2 = new PlayerController();
		playerController2.setName("player2");
		playerController2.setTable(table);
		playerController2.createPlayer();
		table.startGame();
		
		playerController.setAmount("10");
		playerController.bet();
		
		playerController2.setAmount("20");
		playerController2.bet();
		
		assertEquals(30, table.getPot());
		
	}
	
	@Test
	public void winningPlayerTakesPot() throws Exception {
		Player firstPlayer = playerController.getPlayer();
		firstPlayer.dealCard(new CardImpl(Rank.ACE, Suit.CLUBS));
		firstPlayer.dealCard(new CardImpl(Rank.ACE, Suit.DIAMONDS));
		
		PlayerController playerController2 = new PlayerController();
		playerController2.setName("player2");
		playerController2.setTable(table);
		playerController2.createPlayer();
		Player secondPlayer = playerController2.getPlayer();
		secondPlayer.dealCard(new CardImpl(Rank.ACE, Suit.HEARTS));
		secondPlayer.dealCard(new CardImpl(Rank.KING, Suit.CLUBS));
		table.setScoring(new TwoCardsScoring());

		table.startGame();
		playerController.setAmount("10");
		playerController.bet();
		playerController2.setAmount("20");
		playerController2.bet();	        
		table.endGame();
		
		
		assertEquals(20,playerController.getPlayer().getMoney().intValue());
		assertEquals(-20,playerController2.getPlayer().getMoney().intValue());
	}

}
