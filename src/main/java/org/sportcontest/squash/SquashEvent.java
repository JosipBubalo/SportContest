package org.sportcontest.squash;

import org.sportcontest.core.Event;

import java.util.Date;

public abstract class SquashEvent extends Event {
    public SquashEvent(Date time, String description) {
        super(time, description);
    }
}
