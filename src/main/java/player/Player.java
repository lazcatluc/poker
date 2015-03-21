package player;

import cards.Card;

import java.util.Collections;
import java.util.List;

public interface Player {
	
	Player NOBODY = new Player() {
		@Override
		public void dealCard(Card card) {
		}

		@Override
		public String getName() {
			return "nobody";
		}

		@Override
		public List<Card> getHand() {
			return Collections.emptyList();
		}

		@Override
		public void increaseAmount(int amount) {
		}

		@Override
		public void decreaseAmount(int amountInt) {
		}

		@Override
		public Integer getMoney() {
			return Integer.MIN_VALUE;
		}
	};

	public void dealCard(Card card);

	public String getName();

    List<Card> getHand();
    
    void increaseAmount(int amount);

	void decreaseAmount(int amountInt);
	
	Integer getMoney();
}

