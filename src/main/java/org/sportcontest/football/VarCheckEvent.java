package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class VarCheckEvent extends FootballEvent {
    private String team;

    public VarCheckEvent(Date time, String team) {
        super(time, "VAR Check: Goal disallowed for " + team);
        this.team = team;
    }

    @Override
    public String status() {
        return "VAR check: Goal for " + team + " disallowed at " + time;
    }

    @Override
    public void apply(Match match) {
        if (match.getResult() instanceof FootballResult) {
            ((FootballResult) match.getResult()).goalRemoved(team);
        }
    }
}
