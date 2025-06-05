package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class BreakTimeEvent extends TennisEvent {
    public BreakTimeEvent(Date time) {
        super(time, "Break time");
    }

    @Override
    public String status() {
        return "Break time at " + time;
    }

    @Override
    public void apply(Match match) {
        // Just to log event
    }
}
