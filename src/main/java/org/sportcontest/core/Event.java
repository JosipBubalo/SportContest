package org.sportcontest.core;

import java.util.Date;

public abstract class Event {
    protected Date time;
    protected String description;

    public Event(Date time, String description) {
        this.time = time;
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public abstract String status();
}

