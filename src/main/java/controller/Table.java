package controller;

import game.Bets;
import game.Game;
import game.GameAlreadyInProgressException;
import game.GameBuilder;
import game.Owner;
import game.PlayerActionOutOfTurnException;
import game.PlayerIsNotAllowedToBetAmount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import player.InvalidPlayerException;
import player.Player;
import player.PlayerValidator;
import scoring.Scoring;
import betting.Bet;
import cards.Deck;
import cards.Flop;

@ManagedBean(name = "table")
@ApplicationScoped
public class Table implements Owner, Serializable {

    private static final long serialVersionUID = 3L;

    private Player owner;
    private List<Player> players = new ArrayList<>();
    
    @Inject
    private PlayerValidator validator; 
    
    @Inject
    private Deck deck;

    @Inject
    private Scoring scoring;
    
    @Inject 
    private Bets bets;
    
    @Inject
    private GameBuilder gameBuilder;
    private Game game = Game.FINISHED;

    private Flop flop = Flop.EMPTY;
    
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

    public void takeBet(Player player, Bet bet) throws PlayerIsNotAllowedToBetAmount {
        validatePlayerTurn(player);
        bets.takeBet(player, bet);
        game.updateTurn();
    }
    
    public void check(Player player) throws PlayerIsNotAllowedToBetAmount {
        validatePlayerTurn(player);
        bets.check(player);
        game.updateTurn();
    }
    
    private void validatePlayerTurn(Player player) throws PlayerActionOutOfTurnException {
        if (!game.isPlayerTurn(player)) {
            throw new PlayerActionOutOfTurnException();
        }
    }

    public int getPot(){
        return bets.getPot();
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

    public Flop getFlop() {
        return this.flop;
    }

    public void flop() {
        if (this.flop == Flop.EMPTY) {
            this.flop = this.deck.dealFlop();
        }
    }

    @Override
    public Game endGame() {
        List<Player> winners = new ArrayList<>();
        for(Player player : players)
            if(isWinner(player))
                winners.add(player);
        
        for(Player winner : winners)
            winner.increaseAmount(getPot()/winners.size());
        bets.resetPot(getPot() % winners.size());
        game = Game.FINISHED;

        players.forEach((player) -> {
            player.getHand().clear();
        });
        return game;
    }

    public void setBets(Bets bets) {
        this.bets = bets;
    }
    
}
