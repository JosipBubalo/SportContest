package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class EndMatchEventFootball extends FootballEvent {
    public EndMatchEventFootball(Date time) {
        super(time, "Football Match Ended");
    }

    @Override
    public String status() {
        return "Match ended at: " + time;
    }

    @Override
    public void apply(Match match) {
        match.endMatch();
    }
}

