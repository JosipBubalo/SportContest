package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class StartMatchEventFootball extends FootballEvent {
    public StartMatchEventFootball(Date time) {
        super(time, "Football Match Started");
    }

    @Override
    public String status() {
        return "Match started at: " + time;
    }

    @Override
    public void apply(Match match) {
        match.startMatch();

        // Create a new FootballResult if not set
        if (match.getResult() == null && match.getParticipants().size() == 2) {
            String t1 = match.getParticipants().get(0).getName();
            String t2 = match.getParticipants().get(1).getName();
            match.setResult(new FootballResult(t1, t2));
        }
    }
}
