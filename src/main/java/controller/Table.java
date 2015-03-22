package controller;

import game.Game;
import game.GameAlreadyInProgressException;
import game.GameBuilder;
import game.Owner;
import game.PlayerActionOutOfTurnException;

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

	private static final long serialVersionUID = 3L;

	private Player owner;
	private List<Player> players = new ArrayList<>();
	private Map<String, List<Bet>> bets = new HashMap<>();
    private int pot = 0;
	
	@Inject
	private PlayerValidator validator; 
	
	@Inject
	private Deck deck;

	@Inject
	private Scoring scoring;
	
	@Inject
	private GameBuilder gameBuilder;
	private Game game = Game.FINISHED;
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void registerPlayer(Player player) throws InvalidPlayerException, GameAlreadyInProgressException {
		if (isGameStarted()) {
			throw new GameAlreadyInProgressException();
		}
		if (players.isEmpty()) {
			owner = player;
		}
		
		validator.validatePlayer(player);
		
		players.add(player);
	}
	
	public boolean isGameStarted() {
		return !Game.FINISHED.equals(game);
	}
	
	public void setValidator(PlayerValidator validator) {
		this.validator = validator;
	}

	public boolean isOwner(Player player) {
		return player.equals(owner);
	}

	@Override
	public synchronized Game startGame() {
		setGame(getGameBuilder().withPlayers(players).build());
		return getGame();
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public synchronized void fold(Player player) {
		players.remove(player);
		game.removePlayer(player);
		validator.removePlayerName(player.getName());
		if(players.isEmpty()){
			owner = null;
		}else{
			owner = players.get(0);
		}
	}

	public void takeBet(String name, Bet bet) {
		validatePlayerTurn(name);
        List<Bet> playerBets = bets.getOrDefault(name, new ArrayList<Bet>());
        playerBets.add(bet);
		bets.put(name, playerBets);

        pot += bet.getAmount();
        game.updateTurn();
	}
	
	private void validatePlayerTurn(String name) throws PlayerActionOutOfTurnException {
		if (!game.getPlayerOnTurn().getName().equals(name)) {
			throw new PlayerActionOutOfTurnException();
		}
	}

	public Integer getPot(){
		return pot;
	}

	public void setScoring(Scoring scoring) {
		this.scoring = scoring;
	}

	public boolean isWinner(Player player) {
		return scoring.getResult(players).isWinner(player);
	}


	public GameBuilder getGameBuilder() {
		return gameBuilder;
	}

	public void setGameBuilder(GameBuilder gameBuilder) {
		this.gameBuilder = gameBuilder;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public Game endGame() {
		List<Player> winners = new ArrayList<>();
		for(Player player : players)
			if(isWinner(player))
				winners.add(player);
		
		for(Player winner : winners)
			winner.increaseAmount(getPot()/winners.size());
		pot = getPot() % winners.size();
		game = Game.FINISHED;

        players.forEach((player) -> {
            player.getHand().clear();
        });
		return game;
	}
	
}
