package scoring;

import player.Player;

import java.util.Collection;

public class ResultImpl implements Result {
    private Collection<? extends Player> winners;

    public ResultImpl(Collection<? extends Player> winners) {
        this.winners = winners;
    }

    @Override
    public boolean isWinner(Player player) {
        return winners.contains(player);
    }
}
