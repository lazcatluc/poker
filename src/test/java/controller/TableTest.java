package controller;


import java.util.Collection;
import java.util.List;

import cards.Card;
import game.Game;
import game.GameAlreadyInProgressException;
import game.GameBuilder;
import game.GameBuilderImpl;
import player.InvalidPlayerException;
import player.Player;
import player.PlayerImpl;
import player.PlayerValidatorImpl;
import scoring.Result;
import scoring.Scoring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import betting.Bet;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
	
		assertEquals(second, game.getPlayerOnTurn());
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
		
		table.takeBet(first.getName(), mock(Bet.class));
	}
	
	@Test
	public void whenGameEndsWithTwoWinnersAndOddPotKeep1InPot() throws Exception {
		table.registerPlayer(first);
		table.registerPlayer(second);
		game = table.startGame();
		
		Bet oddBet = mock(Bet.class);
		when(oddBet.getAmount()).thenReturn(3);
		table.takeBet(first.getName(), oddBet);
		Bet evenBet = mock(Bet.class);
		when(evenBet.getAmount()).thenReturn(2);
		table.takeBet(second.getName(), evenBet);
		when(result.isWinner(any(Player.class))).thenReturn(true);
		table.endGame();

		assertEquals(1, table.getPot().intValue());
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
