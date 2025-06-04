package org.sportcontest.football;

import java.util.Date;

import org.sportcontest.core.Player;
import org.sportcontest.core.Team;
import org.sportcontest.core.Match;
import org.sportcontest.core.Participant;

public class SubstitutionEvent extends FootballEvent {
    private String playerOut;
    private String playerIn;

    public SubstitutionEvent(Date time, String playerOut, String playerIn) {
        super(time, "Substitution: " + playerOut + " out, " + playerIn + " in");
        this.playerOut = playerOut;
        this.playerIn = playerIn;
    }

    @Override
    public String status() {
        return playerOut + " replaced by " + playerIn + " at " + time;
    }

    @Override
    public void apply(Match match) {
        for (Participant p : match.getParticipants()) {
            if (p instanceof Team) {
                Team team = (Team) p;

                // Find the outgoing player
                Player outPlayer = team.getPlayers().stream()
                        .filter(player -> player.getName().equals(playerOut))
                        .findFirst()
                        .orElse(null);

                if (outPlayer != null) {
                    team.removePlayer(outPlayer);
                    team.addPlayer(new Player(playerIn, 0)); // Rank can be adjusted as needed
                    break; // Only one team should be affected
                }
            }
        }
    }
}

