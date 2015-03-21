package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import cards.Card;
import cards.Deck;

@ManagedBean(name="cards")
@SessionScoped
public class CardController {
	
	@Inject
	private Deck deck;

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public List<Card> getPlayerCards(){
		List<Card> cards = new ArrayList<>();
		cards.add(deck.drawCard());
		cards.add(deck.drawCard());
		return cards;
	}
}
