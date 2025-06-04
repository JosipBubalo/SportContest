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
        // No structural change in match or result, just log the event
        // Could add a phase tracking system in the future if needed
    }
}