package scoring;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import player.Player;
import player.PlayerHand;

public class TwoCardsScoring implements Scoring {
    @Override
    public Result getResult(Collection<? extends Player> players) {
        if(players.size() == 1) {
            return new ResultImpl(players);
        }
        PlayerHand winner = players.stream().map(PlayerHand::new)
                        .max(Comparator.<PlayerHand>naturalOrder()).get();
        List<Player> winners = players.stream().map(PlayerHand::new)
                        .filter(winner::equals)
                        .map(PlayerHand::getPlayer)
                        .collect(Collectors.toList());
        
        return new ResultImpl(winners);
    }

}
