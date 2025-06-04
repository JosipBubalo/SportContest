package org.sportcontest.core;

public class TeamPlayer {
    private String name;
    private int number;
    private String position;

    public TeamPlayer(String name, int number, String position) {
        this.name = name;
        this.number = number;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "#" + number + " " + name + " (" + position + ")";
    }
}
