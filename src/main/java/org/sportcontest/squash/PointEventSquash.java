package org.sportcontest.squash;

import org.sportcontest.core.Match;

import java.util.Date;

public class PointEventSquash extends SquashEvent {
    private String playerName;

    public PointEventSquash(Date time, String playerName) {
        super(time, "Point won by " + playerName);
        this.playerName = playerName;
    }

    @Override
    public String status() {
        return "Point for " + playerName + " at " + time;
    }

    @Override
    public void apply(Match match) {
        // Implementation
    }
}
