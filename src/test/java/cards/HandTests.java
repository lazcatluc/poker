package cards;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTests {

    @Test
    public void comparePairWithNoPair() {
        Hand hand1 = new Hand(new CardImpl(Rank.TWO, Suit.CLUBS), new CardImpl(Rank.TWO, Suit.DIAMONDS));
        Hand hand2 = new Hand(new CardImpl(Rank.ACE, Suit.CLUBS), new CardImpl(Rank.KING, Suit.DIAMONDS));

        assertThat(hand1).isGreaterThan(hand2);
    }

    @Test
    public void compareTwoEqualPairs() {
        Hand hand1 = new Hand(new CardImpl(Rank.TWO, Suit.CLUBS), new CardImpl(Rank.TWO, Suit.DIAMONDS));
        Hand hand2 = new Hand(new CardImpl(Rank.TWO, Suit.HEARTS), new CardImpl(Rank.TWO, Suit.SPADES));

        assertThat(hand1).isEqualTo(hand2);
    }

    @Test
    public void compareTwoNotEqualPairs() {
        Hand hand1 = new Hand(new CardImpl(Rank.TWO, Suit.CLUBS), new CardImpl(Rank.TWO, Suit.DIAMONDS));
        Hand hand2 = new Hand(new CardImpl(Rank.THREE, Suit.HEARTS), new CardImpl(Rank.THREE, Suit.SPADES));

        assertThat(hand1).isLessThan(hand2);
    }

    @Test
    public void compareTwoEqualsNoPair() {
        Hand hand1 = new Hand(new CardImpl(Rank.TWO, Suit.CLUBS), new CardImpl(Rank.THREE, Suit.DIAMONDS));
        Hand hand2 = new Hand(new CardImpl(Rank.THREE, Suit.CLUBS), new CardImpl(Rank.TWO, Suit.DIAMONDS));

        assertThat(hand1).isEqualTo(hand2);
    }

    @Test
    public void compareTwoNotEqualsNoPair() {
        Hand hand1 = new Hand(new CardImpl(Rank.TWO, Suit.CLUBS), new CardImpl(Rank.THREE, Suit.DIAMONDS));
        Hand hand2 = new Hand(new CardImpl(Rank.THREE, Suit.CLUBS), new CardImpl(Rank.ACE, Suit.DIAMONDS));

        assertThat(hand1).isLessThan(hand2);
    }

}
