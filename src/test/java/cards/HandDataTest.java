package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandDataTest {

	@Test
	public void testCompareOnePair() {
		HandData h1 = new HandData(HandType.ONE_PAIR);
		h1.setRank1(Rank.NINE);
		HandData h2 = new HandData(HandType.ONE_PAIR);
		h2.setRank1(Rank.ACE);
		
		assertEquals(-1, h1.compareTo(h2));
	}

}
