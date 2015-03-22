package game;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;

import player.Player;

public class GameBuilderImplTest {
	
	@Test
	public void gameStartsWithFirstPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		assertTrue(buildGame(firstPlayer, secondPlayer).isPlayerTurn(firstPlayer));
	}
	
	@Test
	public void whenUpdateTurnIsCalledTurnGoesToNextPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);
		game.updateTurn();
		
		assertTrue(game.isPlayerTurn(secondPlayer));
	}
	
	@Test
	public void whenUpdateTurnIsCalledTwiceTurnGoesToBackToFirstPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);
		game.updateTurn();
		game.updateTurn();
		
		assertTrue(game.isPlayerTurn(firstPlayer));
	}
	
	@Test
	public void whenSecondPlayerQuitsOnFirstPlayerTurnItIsStillFirstPlayerTurn() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);		
		game.removePlayer(secondPlayer);
		
		assertTrue(game.isPlayerTurn(firstPlayer));
	}
	
	@Test
	public void whenSecondPlayerQuitsOnSecondPlayerTurnItIsThirdPlayerTurn() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		Player thirdPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer,thirdPlayer);	
		game.updateTurn();
		game.removePlayer(secondPlayer);
		
		assertTrue(game.isPlayerTurn(thirdPlayer));
	}
	
	@Test
	public void whenThirdPlayerQuitsOnThirdPlayerTurnItIsFirstPlayerTurn() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		Player thirdPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer,thirdPlayer);	
		game.updateTurn();
		game.updateTurn();
		game.removePlayer(thirdPlayer);
		
		assertTrue(game.isPlayerTurn(firstPlayer));
	}
	
	private Game buildGame(Player... players) {
		return new GameBuilderImpl().withPlayers(Arrays.asList(players)).build();
	}
}
