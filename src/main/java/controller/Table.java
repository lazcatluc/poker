package controller;

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
public class Table implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Deck deck;
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
}
