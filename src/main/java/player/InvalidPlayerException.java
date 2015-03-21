package player;

public class InvalidPlayerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPlayerException() {
		super();
	}
	
	public InvalidPlayerException(String message) {
		super(message);
	}
}
