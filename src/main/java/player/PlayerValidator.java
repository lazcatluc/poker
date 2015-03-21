package player;

import java.util.List;

public interface PlayerValidator {
	
	public void validatePlayer(Player player) throws InvalidPlayerException;
	public List<String> getNameList();

}
