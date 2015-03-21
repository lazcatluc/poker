package controller;

import game.Game;
import game.Owner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import betting.Bet;
import player.InvalidPlayerException;
import player.Player;
import player.PlayerValidator;
import player.PlayerValidatorImpl;
import scoring.Scoring;
import cards.Deck;

@ManagedBean(name="table")
@ApplicationScoped
public class Table implements Owner, Serializable {

	private static final long serialVersionUID = 1L;

	private Player owner;
	private List<Player> players = new ArrayList<>();
	private Map<String,Bet> bets = new HashMap<>();
	
	@Inject
	private PlayerValidator validator; 
	
	@Inject
	private Deck deck;

	@Inject
	private Scoring scoring;
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void registerPlayer(Player player) throws InvalidPlayerException {
		if (players.isEmpty()) {
			owner = player;
		}
		
		validator.validatePlayer(player);
		
		players.add(player);
	}
	
	
	
	public void setValidator(PlayerValidator validator) {
		this.validator = validator;
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

	public void takeBet(String name, Bet bet) {
		bets.put(name, bet);
	}
	
	public Integer getPot(){
		Integer pot = 0;
		for(Map.Entry<String, Bet> bet : bets.entrySet()){
			pot+=bet.getValue().getAmount();
		}
		return pot;
	}

	public void setScoring(Scoring scoring) {
		this.scoring = scoring;
	}

	public boolean isWinner(Player player) {
		return scoring.getResult(players).isWinner(player);
	}
	
}
