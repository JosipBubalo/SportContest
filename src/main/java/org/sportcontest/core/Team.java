package org.sportcontest.core;

import java.util.HashSet;
import java.util.Set;

public class Team extends Participant {
    private Set<TeamPlayer> teamPlayers;

    public Team(String name) {
        super(name);
        this.teamPlayers = new HashSet<>();
    }

    public void addTeamPlayer(TeamPlayer player) {
        teamPlayers.add(player);
    }

    public void removeTeamPlayer(TeamPlayer player) {
        teamPlayers.remove(player);
    }

    public Set<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    @Override
    public String getType() {
        return "Team";
    }
}
