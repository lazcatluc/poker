package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import player.Player;

public class GameBuilderImpl implements Serializable, GameBuilder {
	
	private static final long serialVersionUID = 1L;
	
	private List<Player> players = Arrays.asList(Player.NOBODY);

	@Override
	public GameBuilder withPlayers(Collection<? extends Player> players) { 
		this.players = new ArrayList<>(players);
		return this;
	}

	@Override
	public Game build() {
		return new GameImpl(players);
	}

}
