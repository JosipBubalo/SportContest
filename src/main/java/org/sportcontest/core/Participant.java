package org.sportcontest.core;

public abstract class Participant {
    protected String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();
}
