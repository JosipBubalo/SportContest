package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class GameEventTennis extends TennisEvent {
    private String playerName;

    public GameEventTennis(Date time, String playerName) {
        super(time, "Game won by " + playerName);
        this.playerName = playerName;
    }

    @Override
    public String status() {
        return "Game won by " + playerName + " at " + time;
    }

    @Override
    public void apply(Match match) {
        // Implementation
    }
}
