package org.sportcontest.football;

import org.sportcontest.core.Match;
import org.sportcontest.core.Participant;
import org.sportcontest.core.Team;
import org.sportcontest.core.TeamPlayer;

import java.util.Date;

public class RedCardEvent extends FootballEvent {
    private int playerNumber;
    private boolean fromYellow;

    public RedCardEvent(Date time, int playerNumber) {
        this(time, playerNumber, false);
    }

    public RedCardEvent(Date time, int playerNumber, boolean fromYellow) {
        super(time, "Red card for #" + playerNumber);
        this.playerNumber = playerNumber;
        this.fromYellow = fromYellow;
    }

    @Override
    public String status() {
        return "Red card to #" + playerNumber + " at " + time + (fromYellow ? " (via two yellows)" : "");
    }

    @Override
    public void apply(Match match) {
        match.getRedCardedPlayers().add(String.valueOf(playerNumber));

        for (Participant p : match.getParticipants()) {
            if (p instanceof Team) {
                Team team = (Team) p;
                TeamPlayer toRemove = team.getTeamPlayers().stream()
                        .filter(tp -> tp.getNumber() == playerNumber)
                        .findFirst()
                        .orElse(null);
                if (toRemove != null) {
                    team.removeTeamPlayer(toRemove);
                }
            }
        }
    }
}
