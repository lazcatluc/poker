package cards;

import java.util.Collection;
import java.util.List;

public class Hand implements Comparable<Hand> {
    private Card card1;
    private Card card2;

    public Hand(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public Hand(List<Card> cards) {
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hand hand = (Hand) o;

        return this.compareTo(hand) == 0;
    }

    @Override
    public int hashCode() {
        int result = getMax().hashCode();
        result = 31 * result + getMin().hashCode();
        return result;
    }

    @Override
    public int compareTo(Hand o) {
        if (isPair()) {
            if (o.isPair()) {
                return this.card1.compareTo(o.card1);
            } else {
                return 1;
            }
        } else {
            if (o.isPair()) {
                return -1;
            } else {
                int result = getMax().compareTo(o.getMax());
                if (result == 0) {
                    return getMin().compareTo(o.getMin());
                } else {
                    return result;
                }
            }
        }

    }

    private Card getMax() {
        if (card1.compareTo(card2) > 0) {
            return card1;
        } else {
            return card2;
        }
    }

    private Card getMin() {
        if (card1.compareTo(card2) > 0) {
            return card2;
        } else {
            return card1;
        }
    }

    private boolean isPair() {
        return card1.getRank() == card2.getRank();
    }
}
