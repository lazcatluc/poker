package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import betting.Bet;
import betting.BetImpl;
import cards.Card;
import cards.Deck;
import player.InvalidPlayerException;
import player.Player;
import player.PlayerImpl;
import cards.Card;
import cards.Deck;

@ManagedBean(name = "player")
@SessionScoped
@ServerEndpoint("/echo")
public class PlayerController implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{table}")
	private Table table; 

    private String name;
    private String amount;

    private Player player;
    

	private String error;

    private Session session;

    public PlayerController() {
        System.out.println("Created a new PlayerController");
    }

    public String getError() {
		return error;
	}

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen: " + session.getId());
        this.session = session;

        try{
            session.getBasicRemote().sendText("Hello " + player.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void messageReceiver(String message) {
        System.out.println("Received message:" + message);
    }
	
	

    public String createPlayer() {
        if (player == null) {
            player = new PlayerImpl(getName());
            System.out.println("Created player " + player.getName());
            try {
				table.registerPlayer(player);
			} catch (InvalidPlayerException e) {
				error = "Name already taken";
				return "index";
			}
        }
        return "cards";
    }
    
    public String getName() {
        return name;
    }
    
    public String getAmount() {
		return amount;
	}
    
    public void setAmount(String amount){
		this.amount = amount;
	}

    
	public void setName(String name) {
        this.name = name;
    }
	

    public Deck getDeck() {
		return table.getDeck();
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
	public List<Card> getCards() {
		if (player.getHand().size() == 0) {
			player.dealCard(getDeck().drawCard());
            player.dealCard(getDeck().drawCard());
		}
		return player.getHand();
	}

    public Player getPlayer() {
        return player;
    }

    public void bet(){
		Bet bet = new BetImpl(Integer.parseInt(amount));
		table.takeBet(player.getName(),bet);
	}
    
	public String fold() {
		table.fold(player);
		player = null;
		name = null;
		return "index";
	}
	
	public boolean isWinner() {
		return table.isWinner(player);
	}
	
}
