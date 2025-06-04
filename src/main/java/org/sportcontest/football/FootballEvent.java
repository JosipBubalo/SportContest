package org.sportcontest.football;

import org.sportcontest.core.Event;
import java.util.Date;

public abstract class FootballEvent extends Event {
    public FootballEvent(Date time, String description) {
        super(time, description);
    }
}
