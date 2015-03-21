package controller;

import game.Game;
import game.Owner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import player.Player;
import cards.Deck;

@ManagedBean(name="table")
@ApplicationScoped
public class Table implements Owner, Serializable {

	private static final long serialVersionUID = 1L;

	private Player owner;
	private List<Player> players = new ArrayList<>();
	
	@Inject
	private Deck deck;
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void registerPlayer(Player player) {
		if (players.isEmpty()) {
			owner = player;
		}
		players.add(player);
	}
	
	public boolean isOwner(Player player) {
		return player.equals(owner);
	}

	@Override
	public Game startGame() {
		return Game.FINISHED;
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public void fold(Player player) {
		players.remove(player);
		if(players.isEmpty()){
			owner = null;
		}else{
			owner = players.get(0);
		}
	}
	
}
