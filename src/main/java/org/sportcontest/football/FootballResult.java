package org.sportcontest.football;

import org.sportcontest.core.MatchResult;

public class FootballResult extends MatchResult {
    private String homeTeam;
    private String awayTeam;
    private int homeGoals = 0;
    private int awayGoals = 0;

    public FootballResult(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void goalScored(String team) {
        if (team.equals(homeTeam)) homeGoals++;
        else if (team.equals(awayTeam)) awayGoals++;
    }

    public void goalRemoved(String team) {
        if (team.equals(homeTeam) && homeGoals > 0) homeGoals--;
        else if (team.equals(awayTeam) && awayGoals > 0) awayGoals--;
    }

    @Override
    public String getWinner() {
        return homeGoals > awayGoals ? homeTeam : (awayGoals > homeGoals ? awayTeam : "Draw");
    }

    @Override
    public String getScore() {
        return homeGoals + " - " + awayGoals;
    }

    @Override
    public String getPhase() {
        return "Full-time";
    }
}

