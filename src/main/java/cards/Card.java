package cards;

public interface Card extends Comparable<Card> {
    public Rank getRank();
    public Suit getSuit();
}
