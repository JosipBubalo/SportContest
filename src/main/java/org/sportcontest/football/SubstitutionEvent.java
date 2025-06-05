package org.sportcontest.football;

import java.util.Date;

import org.sportcontest.core.Team;
import org.sportcontest.core.TeamPlayer;
import org.sportcontest.core.Match;
import org.sportcontest.core.Participant;

import java.util.Optional;

public class SubstitutionEvent extends FootballEvent {
    private int numberOut;
    private String playerInName;
    private int playerInNumber;
    private String playerInPosition;

    public SubstitutionEvent(Date time, int numberOut, String playerInName, int playerInNumber, String playerInPosition) {
        super(time, "Substitution: #" + numberOut + " out, " + playerInName + " in");
        this.numberOut = numberOut;
        this.playerInName = playerInName;
        this.playerInNumber = playerInNumber;
        this.playerInPosition = playerInPosition;
    }

    @Override
    public String status() {
        return "Substitution at " + time + ": #" + numberOut + " out, #" + playerInNumber + " " + playerInName + " in";
    }

    @Override
    public void apply(Match match) {
        for (Participant p : match.getParticipants()) {
            if (p instanceof Team) {
                Team team = (Team) p;

                Optional<TeamPlayer> toRemove = team.getTeamPlayers().stream()
                        .filter(tp -> tp.getNumber() == numberOut)
                        .findFirst();

                if (toRemove.isPresent()) {
                    team.removeTeamPlayer(toRemove.get());

                    TeamPlayer newPlayer = new TeamPlayer(playerInName, playerInNumber, playerInPosition);
                    team.addTeamPlayer(newPlayer);
                }
                break;
            }
        }
    }
}

