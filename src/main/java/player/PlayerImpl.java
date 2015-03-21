package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class PlayerImpl implements Player, Serializable {
	private List<Card> hand;
	private String name;

	public PlayerImpl(String nm) {
		name = nm;
		hand = new ArrayList<>();
	}

	public void dealCard(Card card) {
		hand.add(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
