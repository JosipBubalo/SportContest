package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;

public class GoalEvent extends FootballEvent {
    private String team;

    public GoalEvent(Date time, String team) {
        super(time, "Goal scored by " + team);
        this.team = team;
    }

    @Override
    public String status() {
        return "Goal by " + team + " at " + time;
    }

    @Override
    public void apply(Match match) {
        if (match.getResult() instanceof FootballResult) {
            ((FootballResult) match.getResult()).goalScored(team);
        }
    }
}