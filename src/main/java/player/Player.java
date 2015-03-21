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
	};

	public void dealCard(Card card);

	public String getName();

    List<Card> getHand();
}
