package org.sportcontest.core;

import java.util.ArrayList;
import java.util.List;

public class Team extends Participant {
    private List<Player> players;

    public Team(String name) {
        super(name);
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String getType() {
        return "Team";
    }
}
