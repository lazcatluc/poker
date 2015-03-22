package game;

public class GameAlreadyInProgressException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public GameAlreadyInProgressException() {
	}

	public GameAlreadyInProgressException(String s) {
		super(s);
	}

	public GameAlreadyInProgressException(Throwable cause) {
		super(cause);
	}

	public GameAlreadyInProgressException(String message, Throwable cause) {
		super(message, cause);
	}

}
