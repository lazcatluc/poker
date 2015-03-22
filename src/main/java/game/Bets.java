package game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import player.Player;
import betting.Bet;
import betting.BetImpl;

public class Bets {

	private Map<String, List<Bet>> bets = new HashMap<>();
    private int pot = 0;
	
	public int getPot() {
		return pot;
	}

	public void takeBet(Player player, Bet bet) throws PlayerIsNotAllowedToBetAmount {
		shouldAcceptBet(player, bet);
		addBet(player, bet);
        addToPot(bet);
	}

	private void addToPot(Bet bet) {
		pot += bet.getAmount();
	}

	private void addBet(Player player, Bet bet) {
		List<Bet> playerBets = getPlayerBets(player); 		
        playerBets.add(bet);
		bets.put(player.getName(), playerBets);
	}

	private void shouldAcceptBet(Player player, Bet bet) throws PlayerIsNotAllowedToBetAmount {
		int playerBetAmount = getPlayerBetAmount(player);
		int maxBetAmount = getMaxBetAmount();
				
		if(playerBetAmount + bet.getAmount() < maxBetAmount) {
			throw PlayerIsNotAllowedToBetAmount.shouldBetAtLeast(maxBetAmount - playerBetAmount);
		}
	}

	private int getMaxBetAmount() {
		return bets.values().stream().map(this::sumOfBets).max(Comparator.naturalOrder()).orElse(0);
	}

	private int getPlayerBetAmount(Player player) {
		return sumOfBets(getPlayerBets(player));
	}

	private int sumOfBets(List<Bet> listOfBets) {
		return listOfBets.stream().mapToInt(Bet::getAmount).sum();
	}
	private List<Bet> getPlayerBets(Player player) {
		return bets.getOrDefault(player.getName(), new ArrayList<>());
	}

	public void check(Player player) throws PlayerIsNotAllowedToBetAmount {
		takeBet(player, new BetImpl(0));
	}
	
	public void resetPot(int initialPot) {
		bets.clear();
		pot = initialPot;
	}
}
