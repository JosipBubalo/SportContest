package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class HalftimeEvent extends FootballEvent {
    public HalftimeEvent(Date time) {
        super(time, "Halftime reached");
    }

    @Override
    public String status() {
        return "Halftime at " + time;
    }

    @Override
    public void apply(Match match) {
        // Just to log event
    }
}