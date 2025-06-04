package org.sportcontest.tennis;

import org.sportcontest.core.Match;
import org.sportcontest.core.Player;

import java.util.Date;
import java.util.List;

public class StartMatchEventTennis extends TennisEvent {
    public StartMatchEventTennis(Date time) {
        super(time, "Tennis Match Started");
    }

    @Override
    public String status() {
        return "Match started at " + time;
    }

    @Override
    public void apply(Match match) {
        match.startMatch();
        if (match.getResult() == null && match.getParticipants().size() == 2) {
            List<Player> playerA = List.of((Player) match.getParticipants().get(0));
            List<Player> playerB = List.of((Player) match.getParticipants().get(1));
            match.setResult(new TennisResult(playerA, playerB));
        }
    }
}
