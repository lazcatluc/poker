package game;

import java.util.Arrays;

import org.junit.Test;

import player.Player;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameBuilderImplTest {
	
	@Test
	public void gameStartsWithFirstPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		assertEquals(firstPlayer, buildGame(firstPlayer, secondPlayer).getPlayerOnTurn());
	}
	
	@Test
	public void whenUpdateTurnIsCalledTurnGoesToNextPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);
		game.updateTurn();
		
		assertEquals(secondPlayer, game.getPlayerOnTurn());
	}
	
	@Test
	public void whenUpdateTurnIsCalledTwiceTurnGoesToBackToFirstPlayer() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);
		game.updateTurn();
		game.updateTurn();
		
		assertEquals(firstPlayer, game.getPlayerOnTurn());
	}
	
	@Test
	public void whenSecondPlayerQuitsOnFirstPlayerTurnItIsStillFirstPlayerTurn() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer);		
		game.removePlayer(secondPlayer);
		
		assertEquals(firstPlayer, game.getPlayerOnTurn());
	}
	
	@Test
	public void whenSecondPlayerQuitsOnSecondPlayerTurnItIsThirdPlayerTurn() throws Exception {
		Player firstPlayer = mock(Player.class);
		Player secondPlayer = mock(Player.class);
		Player thirdPlayer = mock(Player.class);
		
		Game game = buildGame(firstPlayer,secondPlayer,thirdPlayer);	
		game.updateTurn();
		game.removePlayer(secondPlayer);
		
		assertEquals(thirdPlayer, game.getPlayerOnTurn());
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
		
		assertEquals(firstPlayer, game.getPlayerOnTurn());
	}
	
	private Game buildGame(Player... players) {
		return new GameBuilderImpl().withPlayers(Arrays.asList(players)).build();
	}
}
