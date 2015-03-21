package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cards.Card;
import cards.Deck;

@ManagedBean(name = "player")
@SessionScoped
public class PlayerController implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Card> cards;

	@ManagedProperty("#{table}")
	private Table table;

	public Deck getDeck() {
		return table.getDeck();
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
	public List<Card> getCards() {
		if (cards == null) {
			cards = new ArrayList<>();
			cards.add(getDeck().drawCard());
			cards.add(getDeck().drawCard());
		}
		return cards;
	}
}
