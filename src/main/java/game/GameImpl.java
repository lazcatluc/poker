package game;

import java.io.Serializable;
import java.util.List;

import player.Player;

public class GameImpl implements Game, Serializable {

	private static final long serialVersionUID = 1L;
	private final List<Player> players;
	private int turn = 0;

	public GameImpl(List<Player> players) {
		this.players = players;
	}

	private synchronized Player getPlayerOnTurn() {
		return players.get(turn);
	}
	
	@Override
	public boolean isPlayerTurn(Player player) {
		return player.equals(getPlayerOnTurn());
	}

	@Override
	public synchronized void updateTurn() {
		turn++;
		checkTurnBound();
	}

	@Override
	public synchronized void removePlayer(Player player) {
		int index = players.indexOf(player);
		players.remove(player);
		if (turn > index) {
			turn--;
		}
		checkTurnBound();
	}

	private void checkTurnBound() {
		if (turn == players.size()) {
			turn = 0;
		}
	}
}
