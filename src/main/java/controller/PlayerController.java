package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import cards.Card;
import cards.Deck;
import player.Player;
import player.PlayerImpl;

@ManagedBean(name = "player")
@SessionScoped
public class PlayerController implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{table}")
	private Table table;

    private String name;

    private Player player;

    public String createPlayer() {
        if (player == null) {
            player = new PlayerImpl(getName());
        }
        return "cards";
    }

    public String getName() {
        return name;
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
}
