package game;

public interface Owner {
	Owner MISSING = new Owner() {
		@Override
		public Game startGame() {
			return Game.FINISHED;
		}
	};

	Game startGame();
	
}
