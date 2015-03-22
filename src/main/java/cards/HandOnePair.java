package cards;

import java.util.Arrays;
import java.util.Collections;

public class HandOnePair extends HandData {
	
	private Rank pairRank;
	private Rank[] oddRanks;

	public HandOnePair(Rank pairRank, Rank[] oddRanks) throws Exception {
		super(HandType.ONE_PAIR);
		this.pairRank = pairRank;
		if(oddRanks.length!=3) 
			throw new Exception("Only 3 odd cards are allowed in a ONE_PAIR hand.");
		this.oddRanks = oddRanks;
		Arrays.sort(this.oddRanks,Collections.reverseOrder());
		
	}
	
	public int compareTo(HandData o) {
		int result = compareTypes(o);
		if(result!=0) return result;
		
		HandOnePair other = (HandOnePair)o;
		
		if(pairRank != other.pairRank)
			return getSign(pairRank.compareTo(other.pairRank));
		else
			for(int i=0;i<3;i++) {
				int oddCompResult = oddRanks[i].compareTo(other.oddRanks[i]); 
				if(oddCompResult !=0 )
					return getSign(oddCompResult);
			}
		
		// It Should never get here
		return 0;		
	}

}
