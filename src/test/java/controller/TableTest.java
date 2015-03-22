package controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.Bets;
import game.Game;
import game.GameAlreadyInProgressException;
import game.GameBuilderImpl;
import game.PlayerIsNotAllowedToBetAmount;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import player.InvalidPlayerException;
import player.Player;
import player.PlayerImpl;
import player.PlayerValidatorImpl;
import scoring.Result;
import scoring.Scoring;
import betting.Bet;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class TableTest {
	
	Table table;	
	Result result;
	Player first;
	Player second;
	Game game;
	
	@Before
	public void setup(){
		table = new Table();
		table.setValidator(new PlayerValidatorImpl());
		table.setGameBuilder(new GameBuilderImpl());
		table.setBets(new Bets());
		Scoring scoring = mock(Scoring.class);
		table.setScoring(scoring);
		result = mock(Result.class);
		when(scoring.getResult(any(Collection.class))).thenReturn(result);
		
		first = mock(Player.class);
		when(first.getName()).thenReturn("Player1");
		second = mock(Player.class);
		when(second.getName()).thenReturn("Player2");
	}
	
	@Test
	public void initiallyTableHasNoOwner() throws Exception {
		assertFalse(table.isOwner(mock(Player.class)));
	}
	
	@Test
	public void afterRegisteringTheFirstPlayerBecomesOwner() throws Exception {
		table.registerPlayer(first);
		
		assertTrue(table.isOwner(first));
	}
	
	@Test
	public void afterRegisteringTheSecondPlayerDoesNotBecomeOwner() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		
		assertFalse(table.isOwner(second));
	}
	
	@Test
	public void numberOfPlayersIsZeroAtStart() throws Exception {
		int numberOfPlayers = table.getNumberOfPlayers();
		
		assertEquals(0,numberOfPlayers);
	}
	
	@Test
	public void whenPlayerFoldsPlayerIsRemovedFromGame() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		game = table.startGame();
		table.fold(first);
	
		assertTrue(game.isPlayerTurn(second));
	}
	
	@Test
	public void whenPlayerFoldsPlayerCanGetBackIn() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		
		table.fold(second);
		
		table.registerPlayer(second);
	}
	
	@Test(expected = GameAlreadyInProgressException.class)
	public void whenGameStartedPlayerCannotRegisterAnymore() throws Exception {
		table.registerPlayer(first);
		table.startGame();
		table.registerPlayer(second);
	}
	
	@Test
	public void afterRegistrationAndGameStartFirstPlayerCanBet() throws Exception {
		table.registerPlayer(first);
		game = table.startGame();
		boolean succes = true;

		table.takeBet(first, mock(Bet.class));
		assertTrue(succes);
	}
	
		
	@Test
	public void whenGameEndsWithTwoWinnersAndOddPotKeep1InPot() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		game = table.startGame();
		
		Bet oddBet = makeBet(3);
		table.takeBet(first, oddBet);
		Bet evenBet = makeBet(4);
		table.takeBet(second, evenBet);
		when(result.isWinner(any(Player.class))).thenReturn(true);
		table.endGame();
		
		assertEquals(1, table.getPot());
	}

	private static Bet makeBet(int amount) {
		Bet bet = mock(Bet.class);
		when(bet.getAmount()).thenReturn(amount);
		return bet;
	}
	
	@Test
	public void whenGameStartsAfterFirstPlayerChecksNextPlayerCanCheck() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		game = table.startGame();

		table.check(first);
		table.check(second);
		assertThat(table.getPot()).isZero();
	}

	@Test(expected=PlayerIsNotAllowedToBetAmount.class)
	public void whenGameStartsAfterFirstBetsNextPlayerCantCheck() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		game = table.startGame();

		table.takeBet(first, makeBet(2));
		table.check(second);
	}
	
	public class TableWithOnePlayer{
		
		Player player;
		
		@Before
		public void setup() throws InvalidPlayerException{
			player = new PlayerImpl("player");
			table.registerPlayer(player);
		}
		
		@Test
		public void numberOfPlayersIsOne() throws Exception {
			int numberOfPlayers = table.getNumberOfPlayers();
			
			assertEquals(1,numberOfPlayers);
		}
	}
	
	
}
