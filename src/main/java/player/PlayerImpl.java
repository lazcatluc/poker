package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class PlayerImpl implements Player, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List<Card> hand;
	private final String name;
	private Integer money = 0;
	

	public PlayerImpl(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public void dealCard(Card card) {
		hand.add(card);
	}
	
	public String getName() {
		return name;
	}

    @Override
    public List<Card> getHand() {
        return hand;
    }
    
    @Override
    public boolean equals(Object obj) {
    	return obj instanceof Player && name.equals(((Player) obj).getName());
    }
    
    @Override
    public String toString() {
    	return "Player :" + name + " / cards : " + hand;
    }
    
    public void increaseAmount(int amount){
    	this.money += amount;
    }

	@Override
	public void decreaseAmount(int amount) {
		this.money -= amount;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
}

