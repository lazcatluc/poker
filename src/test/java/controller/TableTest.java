package controller;

import player.Player;
import player.PlayerValidator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TableTest {
	
	private Table table;
	
	@Before
	public void setUp() throws Exception {
		table = new Table();
		PlayerValidator validator = mock(PlayerValidator.class);
		table.setValidator(validator);
	}
	
	@Test
	public void initiallyTableHasNoOwner() throws Exception {
		assertFalse(new Table().isOwner(mock(Player.class)));
	}
	
	@Test
	public void afterRegisteringTheFirstPlayerBecomesOwner() throws Exception {
		Player player = mock(Player.class);
		
		table.registerPlayer(player);
		
		assertTrue(table.isOwner(player));
	}
	
	@Test
	public void afterRegisteringTheSecondPlayerDoesNotBecomeOwner() throws Exception {
		Player first = mock(Player.class);
		Player second = mock(Player.class);
		
		table.registerPlayer(first);
		table.registerPlayer(second);
		
		assertFalse(table.isOwner(second));
	}
}
