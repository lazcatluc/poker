package cards;

import cards.Rank;
import cards.Suit;

public class HandData implements Comparable<HandData> {

	private HandType htype;

	private Suit suit;
	private Rank rank1;
	private Rank rank2;
	
	public HandData(HandType htype) {
		this.htype = htype;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank1() {
		return rank1;
	}

	public void setRank1(Rank rank1) {
		this.rank1 = rank1;
	}

	public Rank getRank2() {
		return rank2;
	}

	public void setRank2(Rank rank2) {
		this.rank2 = rank2;
	}



	@Override
	public int compareTo(HandData o) {
		
		// Different hand type
		int result = htype.compareTo(o.htype);
		if(result!=0) return (int) Math.signum(result);
		
		// Equal hand types
		switch (htype) {
		case NOTHING:
			return 0;
		case ONE_PAIR:
			return (int) Math.signum(rank1.compareTo(o.rank1));
		case TWO_PAIRS:
			
			break;
		case THREE_OF_A_KIND:
			break;
		case STRAIGHT:
			break;
		case FLUSH:
			break;
		case FULL_HOUSE:
			break;
		case FOUR_OF_A_KIND:
			break;
		case ROYAL_FLUSH:
			break;

		}
		
		return 0;
	}

}
