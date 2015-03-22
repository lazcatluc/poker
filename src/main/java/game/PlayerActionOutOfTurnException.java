package game;

public class PlayerActionOutOfTurnException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public PlayerActionOutOfTurnException() {
	}

	public PlayerActionOutOfTurnException(String s) {
		super(s);
	}

	public PlayerActionOutOfTurnException(Throwable cause) {
		super(cause);
	}

	public PlayerActionOutOfTurnException(String message, Throwable cause) {
		super(message, cause);
	}

}
