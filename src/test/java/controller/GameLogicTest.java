package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;
import org.mockito.Mockito;

import player.Player;
import player.PlayerImpl;
import scoring.Result;
import scoring.ResultImpl;
import scoring.Scoring;

public class GameLogicTest {

	@Test
	public void tableDelegatesDecisionToScoringService() {
		Table table = new Table();
		Scoring scoring = Mockito.mock(Scoring.class);
		Player winner = new PlayerImpl("winner");
		Player looser = new PlayerImpl("loser");
		Result result = new ResultImpl(Collections.singleton(winner));
		Mockito.when(scoring.getResult(Mockito.<Player>anyCollection())).thenReturn(result );
		table.setScoring(scoring);
		
		assertTrue(table.isWinner(winner));
		assertFalse(table.isWinner(looser));
	}

}
