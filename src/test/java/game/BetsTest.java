package game;

import org.junit.Test;

import betting.BetImpl;
import player.PlayerImpl;


public class BetsTest {

	@Test(expected=PlayerIsNotAllowedToBetAmount.class)
	public void playerIsNotAllowedToBetLessThanMaxOfOtherPlayersBets() throws Exception {
		Bets bets = new Bets();
		bets.takeBet(new PlayerImpl("first"), new BetImpl(5));
		bets.takeBet(new PlayerImpl("second"), new BetImpl(3));
	}
}
