package player;

public class PlayerAlreadyJoined extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerAlreadyJoined() {
		super();
	}
	
	public PlayerAlreadyJoined(String message) {
		super(message);
	}
}
