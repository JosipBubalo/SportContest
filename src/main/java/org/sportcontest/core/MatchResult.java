package org.sportcontest.core;

public abstract class MatchResult {
    protected Match match;

    public void setMatch(Match match) {
        this.match = match;
    }

    public abstract String getWinner();
    public abstract String getScore();
    public abstract String getPhase();
}

