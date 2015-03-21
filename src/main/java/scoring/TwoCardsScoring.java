package scoring;

import player.Player;

import java.util.Collection;

public class TwoCardsScoring implements Scoring {
    @Override
    public Result getResult(Collection<? extends Player> players) {
        return new ResultImpl(players);
    }
}
