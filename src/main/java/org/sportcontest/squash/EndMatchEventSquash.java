package org.sportcontest.squash;

import org.sportcontest.core.Match;

import java.util.Date;

public class EndMatchEventSquash extends SquashEvent {
    public EndMatchEventSquash(Date time) {
        super(time, "Squash Match Ended");
    }

    @Override
    public String status() {
        return "Squash match ended at " + time;
    }

    @Override
    public void apply(Match match) {
        match.endMatch();
    }
}