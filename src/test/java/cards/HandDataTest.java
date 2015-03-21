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
	
	@Test
	public void testCompareTwoPairsDiff() {
		HandData h1 = new HandData(HandType.TWO_PAIRS);
		h1.setRank1(Rank.NINE);
		h1.setRank2(Rank.SEVEN);
		HandData h2 = new HandData(HandType.TWO_PAIRS);
		h2.setRank1(Rank.ACE);
		h2.setRank2(Rank.FIVE);
		
		assertEquals(-1, h1.compareTo(h2));
		
	}
	
	@Test
	public void testCompareTwoPairsEqMax() {
		HandData h1 = new HandData(HandType.TWO_PAIRS);
		h1.setRank1(Rank.NINE);
		h1.setRank2(Rank.SEVEN);
		HandData h2 = new HandData(HandType.TWO_PAIRS);
		h2.setRank1(Rank.NINE);
		h2.setRank2(Rank.FIVE);
		
		assertEquals(1, h1.compareTo(h2));
	}
	
	@Test
	public void testCompareTwoPairsEq() {
		HandData h1 = new HandData(HandType.TWO_PAIRS);
		h1.setRank1(Rank.NINE);
		h1.setRank2(Rank.SEVEN);
		h1.setRank3(Rank.ACE);
		HandData h2 = new HandData(HandType.TWO_PAIRS);
		h2.setRank1(Rank.NINE);
		h2.setRank2(Rank.SEVEN);
		h2.setRank3(Rank.KING);
		
		assertEquals(1, h1.compareTo(h2));
		
	}
}
