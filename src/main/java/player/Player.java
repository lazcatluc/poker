package player;

import cards.Card;

import java.util.List;

public interface Player {
	
	public void dealCard(Card card);

	public String getName();

    List<Card> getHand();
    
    void increaseAmount(int amount);

	void decreaseAmount(int amountInt);
	
	Integer getMoney();
}
