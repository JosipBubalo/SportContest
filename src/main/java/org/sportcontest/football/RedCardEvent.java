package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class RedCardEvent extends FootballEvent {
    private String player;
    private boolean fromYellow;

    public RedCardEvent(Date time, String player) {
        this(time, player, false);
    }

    public RedCardEvent(Date time, String player, boolean fromYellow) {
        super(time, "Red card for " + player);
        this.player = player;
        this.fromYellow = fromYellow;
    }

    @Override
    public String status() {
        return "Red card to " + player + " at " + time + (fromYellow ? " (via two yellows)" : "");
    }

    @Override
    public void apply(Match match) {
        match.getRedCardedPlayers().add(player);
    }
}
