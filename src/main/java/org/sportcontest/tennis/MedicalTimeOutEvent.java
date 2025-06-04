package org.sportcontest.tennis;

import org.sportcontest.core.Match;

import java.util.Date;

public class MedicalTimeOutEvent extends TennisEvent {
    public MedicalTimeOutEvent(Date time) {
        super(time, "Medical timeout");
    }

    @Override
    public String status() {
        return "Medical timeout at " + time;
    }

    @Override
    public void apply(Match match) {
        // Log only
    }
}
