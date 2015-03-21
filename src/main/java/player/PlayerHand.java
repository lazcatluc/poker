package player;

import cards.Hand;

import java.util.Comparator;

public class PlayerHand implements Comparable<PlayerHand> {

    private final Player player;
    private final Hand hand;

    public PlayerHand(Player player) {
        this.player = player;
        this.hand = new Hand(player.getHand());
    }

    @Override
    public int compareTo(PlayerHand o) {
        return this.hand.compareTo(o.hand);
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerHand that = (PlayerHand) o;

        if (!hand.equals(that.hand)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hand.hashCode();
    }
}
