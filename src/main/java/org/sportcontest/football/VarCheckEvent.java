package org.sportcontest.football;

import org.sportcontest.core.Match;
import org.sportcontest.core.Participant;
import org.sportcontest.core.Team;
import org.sportcontest.core.TeamPlayer;

import java.util.Date;

public class VarCheckEvent extends FootballEvent {
    private String checkType; // "goal" or "redcard"
    private String teamName;  // for goal

    private String playerName; // for red card
    private Integer playerNumber; // for red card

    private String playerPosition; // for red card

    public VarCheckEvent(Date time, String teamName) {
        super(time, "VAR Check: Goal disallowed for " + teamName);
        this.checkType = "goal";
        this.teamName = teamName;
    }

    public VarCheckEvent(Date time, String playerName, int playerNumber, String playerPosition) {
        super(time, "VAR Check: Red card overturned for #" + playerNumber);
        this.checkType = "redcard";
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerPosition = playerPosition;
    }

    @Override
    public String status() {
        return switch (checkType) {
            case "goal" -> "VAR disallowed goal for " + teamName + " at " + time;
            case "redcard" -> "VAR overturned red card for #" + playerNumber + " at " + time;
            default -> "VAR check at " + time;
        };
    }

    @Override
    public void apply(Match match) {
        if ("goal".equals(checkType)) {
            // Reverse a goal for the team
            if (match.getResult() instanceof FootballResult) {
                ((FootballResult) match.getResult()).goalRemoved(teamName);
            }
        } else if ("redcard".equals(checkType)) {
            // Remove from red carded list and (optionally) restore player to team
            String playerKey = String.valueOf(playerNumber);
            match.getRedCardedPlayers().remove(playerKey);

            for (Participant p : match.getParticipants()) {
                if (p instanceof Team team) {
                    // Avoid re-adding player if already present
                    boolean alreadyThere = team.getTeamPlayers().stream()
                            .anyMatch(tp -> tp.getNumber() == playerNumber);
                    if (!alreadyThere) {
                        // This assumes we know name/position — could store in a map if needed
                        team.addTeamPlayer(new TeamPlayer(playerName, playerNumber, playerPosition));
                    }
                }
            }
        }
    }
}
