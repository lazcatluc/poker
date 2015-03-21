package cards;

import cards.Rank;
import cards.Suit;

public class HandData implements Comparable<HandData> {

	private HandType htype;

	private Suit suit;
	private Rank rank1;
	private Rank rank2;
	private Rank rank3;
	
	public Rank getRank3() {
		return rank3;
	}

	public void setRank3(Rank rank3) {
		this.rank3 = rank3;
	}

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
	
	private int getSign(int x) {
		if(x==0) return 0;
		else return x>0 ? 1 : -1;
	}


	@Override
	public int compareTo(HandData o) {
		
		// Different hand type
		int result = htype.compareTo(o.htype);
		if(result!=0) return getSign(result);
		
		// Equal hand types
		switch (htype) {
		case NOTHING:
			return 0;
		case ONE_PAIR:
			return getSign(rank1.compareTo(o.rank1));
		case TWO_PAIRS:
			if(rank1!=null && rank1!=o.rank1)
				return getSign(rank1.compareTo(o.rank1));
			else if(rank1==o.rank1)
				if(rank2!=null && rank2!=o.rank2)
					return getSign(rank2.compareTo(o.rank2));
				else if(rank2==o.rank2)
					if(rank3!=null && rank3!=o.rank3)
						return getSign(rank3.compareTo(o.rank3));
					else
						return 0;
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
