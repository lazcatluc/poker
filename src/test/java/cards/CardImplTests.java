package cards;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardImplTests {

    @Test
    public void testEquals() {
        Card c1 = new CardImpl(Rank.ACE, Suit.CLUBS);
        Card c2 = new CardImpl(Rank.ACE, Suit.CLUBS);
        assertEquals(c1, c2);
    }

    @Test
    public void testNotEquals() {
        Card c1 = new CardImpl(Rank.ACE, Suit.DIAMONDS);
        Card c2 = new CardImpl(Rank.ACE, Suit.CLUBS);
        Card c3 = new CardImpl(Rank.TEN, Suit.CLUBS);
        assertNotEquals(c1, c2);
        assertNotEquals(c2, c3);
        assertNotEquals(c1, c3);
    }
}
