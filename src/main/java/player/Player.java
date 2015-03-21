package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

public interface Player {
	
	public void dealCard(Card card);
	public String getName();
	
}
