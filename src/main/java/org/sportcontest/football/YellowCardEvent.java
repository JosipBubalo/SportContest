package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class YellowCardEvent extends FootballEvent {
    private int playerNumber;

    public YellowCardEvent(Date time, int playerNumber) {
        super(time, "Yellow card for #" + playerNumber);
        this.playerNumber = playerNumber;
    }

    @Override
    public String status() {
        return "Yellow card to #" + playerNumber + " at " + time;
    }

    @Override
    public void apply(Match match) {
        Map<String, Integer> yellowMap = match.getYellowCardCount();
        Set<String> redSet = match.getRedCardedPlayers();

        String key = String.valueOf(playerNumber);
        int count = yellowMap.getOrDefault(key, 0) + 1;
        yellowMap.put(key, count);

        if (count >= 2 && !redSet.contains(key)) {
            RedCardEvent redCard = new RedCardEvent(time, playerNumber, true);
            redCard.apply(match);              // Actually ejects player
            match.applyEvent(redCard);         // Logs red card event
        }
    }
}

