package org.sportcontest.tennis;

import org.sportcontest.core.Event;

import java.util.Date;

public abstract class TennisEvent extends Event {
    public TennisEvent(Date time, String description) {
        super(time, description);
    }
}
