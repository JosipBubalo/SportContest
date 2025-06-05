package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class TieBreakEvent extends TennisEvent {
    public TieBreakEvent(Date time) {
        super(time, "Tie-break initiated");
    }

    @Override
    public String status() {
        return "Tie-break initiated at " + time;
    }

    @Override
    public void apply(Match match) {
        // Just to log event
    }
}
