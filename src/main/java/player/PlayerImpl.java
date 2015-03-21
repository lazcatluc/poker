package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class PlayerImpl implements Player, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List<Card> hand;
	private final String name;
	

	public PlayerImpl(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public void dealCard(Card card) {
		hand.add(card);
	}
	
	public String getName() {
		return name;
	}

    @Override
    public List<Card> getHand() {
        return hand;
    }
}
