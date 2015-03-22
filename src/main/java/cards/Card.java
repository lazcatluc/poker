package cards;

public interface Card extends Comparable<Card> {
    public Rank getRank();
    public Suit getSuit();

    public final Card NO_CARD = new Card() {
        @Override
        public Rank getRank() {
            throw new IllegalStateException("NO_CARD has no rank.");
        }

        @Override
        public Suit getSuit() {
            throw new IllegalStateException("NO_CARD has no suit.");
        }

        @Override
        public int compareTo(Card o) {
            throw new IllegalStateException("NO_CARD comparison.");
        }
    };
}
