package cards;

import java.io.Serializable;
import java.util.*;

public class DeckImpl implements Deck, Serializable {
    private static final long serialVersionUID = 1900982426744622247L;
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
            return Card.NO_CARD;
        }
        return cards.get(drawn++);
    }

    @Override
    public Flop dealFlop() {
        return new Flop(drawCard(), drawCard(), drawCard());
    }
}
