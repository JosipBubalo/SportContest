package org.sportcontest.core;

public class Player extends Participant {
    private int ranking;

    public Player(String name, int ranking) {
        super(name);
        this.ranking = ranking;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String getType() {
        return "Player";
    }
}
