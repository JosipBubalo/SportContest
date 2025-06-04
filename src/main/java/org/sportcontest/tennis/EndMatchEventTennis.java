package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class EndMatchEventTennis extends TennisEvent {
    public EndMatchEventTennis(Date time) {
        super(time, "Tennis Match Ended");
    }

    @Override
    public String status() {
        return "Match ended at " + time;
    }

    @Override
    public void apply(Match match) {
        match.endMatch();
    }
}
