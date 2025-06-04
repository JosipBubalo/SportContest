package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class SetEvent extends TennisEvent {
    private int gamesA;
    private int gamesB;

    public SetEvent(Date time, int gamesA, int gamesB) {
        super(time, "Set ended: " + gamesA + "-" + gamesB);
        this.gamesA = gamesA;
        this.gamesB = gamesB;
    }

    @Override
    public String status() {
        return "Set ended: " + gamesA + "-" + gamesB + " at " + time;
    }

    @Override
    public void apply(Match match) {
        if (match.getResult() instanceof TennisResult result) {
            result.addSetResult(gamesA, gamesB);
        }
    }
}