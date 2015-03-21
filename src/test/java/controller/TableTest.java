package controller;

import player.Player;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TableTest {
	@Test
	public void initiallyTableHasNoOwner() throws Exception {
		assertFalse(new Table().isOwner(mock(Player.class)));
	}
	
	@Test
	public void afterRegisteringTheFirstPlayerBecomesOwner() throws Exception {
		Table table = new Table();
		Player player = mock(Player.class);
		
		table.registerPlayer(player);
		
		assertTrue(table.isOwner(player));
	}
	
	@Test
	public void afterRegisteringTheSecondPlayerDoesNotBecomeOwner() throws Exception {
		Table table = new Table();
		Player first = mock(Player.class);
		Player second = mock(Player.class);
		
		table.registerPlayer(first);
		table.registerPlayer(second);
		
		assertFalse(table.isOwner(second));
	}
}
