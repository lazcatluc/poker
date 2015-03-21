package cards;

import java.util.*;

public class DeckImpl implements Deck {
    private List<Card> cards;
    private int drawn = 0;

    public DeckImpl() {
        cards = new ArrayList<>(52);
        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                cards.add(new CardImpl(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    @Override
    public Card drawCard() {
        if (drawn == 52) {
            return null;
        }
        return cards.get(drawn++);
    }
}
