package scoring;

import cards.Hand;
import player.Player;
import player.PlayerHand;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TwoCardsScoring implements Scoring {
    @Override
    public Result getResult(Collection<? extends Player> players) {
        if(players.size() == 1) {
            return new ResultImpl(players);
        }
        AtomicReference<PlayerHand> max = new AtomicReference<>();

        PlayerHand winner = players.stream().map(PlayerHand::new)
                        .max(Comparator.<PlayerHand>naturalOrder()).get();
        List<Player> winners = players.stream().map(PlayerHand::new)
                        .filter(winner::equals)
                        .map(PlayerHand::getPlayer)
                        .collect(Collectors.toList());

        return new ResultImpl(winners);
    }

}
