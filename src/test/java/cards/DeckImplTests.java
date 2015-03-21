package cards;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeckImplTests {

    @Test
    public void testDrawCard() {
        Set<Card> drawn = new HashSet<Card>();
        Deck deck = new DeckImpl();
        for (int i = 0; i < 52; i++) {
            drawn.add(deck.drawCard());
        }
        assertEquals(52, drawn.size());
        assertFalse(drawn.contains(null));

        assertEquals(null, deck.drawCard());
    }
}
