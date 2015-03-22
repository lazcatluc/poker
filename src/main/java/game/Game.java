package game;

import player.Player;

public interface Game {

	Game FINISHED = new Game() {
		@Override
		public Player getPlayerOnTurn() {
			return Player.NOBODY;
		}

		@Override
		public void updateTurn() {			
		}

		@Override
		public void removePlayer(Player player) {
		}
	};
	
	Player getPlayerOnTurn();
	void removePlayer(Player player);
	void updateTurn();
}
