package betting;

import java.io.Serializable;

public class BetImpl implements Bet, Serializable {
	
	private static final long serialVersionUID = 1L;

	private int amount;
	
	public BetImpl(int amount) {
		amount = amount>=0 ? amount : 0;
	}

	@Override
	public int getAmount() {
		return amount;
	}
}
