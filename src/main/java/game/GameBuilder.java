package game;

import java.util.Collection;

import player.Player;

public interface GameBuilder {
	GameBuilder withPlayers(Collection<? extends Player> players);
	Game build();
}
