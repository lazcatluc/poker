package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerValidatorImpl implements PlayerValidator {

	private List<String> nameList;

	public PlayerValidatorImpl() {
		nameList = new ArrayList<>();
	}

	@Override
	public void validatePlayer(Player player) throws InvalidPlayerException {
		if(!nameList.contains(player.getName())) 
			nameList.add(player.getName());
		else
			throw new InvalidPlayerException("Player \"" + player.getName() + "\" has already joined.");
	}

	@Override
	public List<String> getNameList() {
		return nameList;
	}

	@Override
	public void removePlayerName(String playerName) {
		nameList.remove(playerName);
	}

}
