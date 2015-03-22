package game;

import player.Player;

public interface Game {

	Game FINISHED = new Game() {
		
		public boolean isPlayerTurn(Player player) {
			return false;
		}

		@Override
		public void updateTurn() {			
		}

		@Override
		public void removePlayer(Player player) {
		}
	};
	
	boolean isPlayerTurn(Player player);
	void removePlayer(Player player);
	void updateTurn();
}
