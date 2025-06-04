package org.sportcontest.squash;

import org.sportcontest.core.Match;

import java.util.Date;

public class GameEventSquash extends SquashEvent {
    private int pointsA;
    private int pointsB;

    public GameEventSquash(Date time, int pointsA, int pointsB) {
        super(time, "Game ended " + pointsA + "-" + pointsB);
        this.pointsA = pointsA;
        this.pointsB = pointsB;
    }

    @Override
    public String status() {
        return "Game score: " + pointsA + "-" + pointsB + " at " + time;
    }

    @Override
    public void apply(Match match) {
        if (match.getResult() instanceof SquashResult result) {
            result.addGameResult(pointsA, pointsB);
        }
    }
}
