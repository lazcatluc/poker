package game;

public class PlayerIsNotAllowedToBetAmount extends Exception {

	private static final long serialVersionUID = 1L;

	private PlayerIsNotAllowedToBetAmount(int minBetAmount) {
		super(String.format("Minimum bet amount should be %d", minBetAmount));
	}

	public static PlayerIsNotAllowedToBetAmount shouldBetAtLeast(int minBetAmount) {
		return new PlayerIsNotAllowedToBetAmount(minBetAmount);
	}
}
