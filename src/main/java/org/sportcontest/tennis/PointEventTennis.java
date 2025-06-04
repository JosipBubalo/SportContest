package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class PointEventTennis extends TennisEvent {
    private String playerName;

    public PointEventTennis(Date time, String playerName) {
        super(time, "Point won by " + playerName);
        this.playerName = playerName;
    }

    @Override
    public String status() {
        return "Point won by " + playerName + " at " + time;
    }

    @Override
    public void apply(Match match) {
        // Can be extended to track points
    }
}
