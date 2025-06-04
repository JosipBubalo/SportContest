package org.sportcontest.football;

import org.sportcontest.core.Match;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class YellowCardEvent extends FootballEvent {
    private String player;

    public YellowCardEvent(Date time, String player) {
        super(time, "Yellow card for " + player);
        this.player = player;
    }

    @Override
    public String status() {
        return "Yellow card to " + player + " at " + time;
    }

    @Override
    public void apply(Match match) {
        Map<String, Integer> countMap = match.getYellowCardCount();
        Set<String> redCarded = match.getRedCardedPlayers();

        int newCount = countMap.getOrDefault(player, 0) + 1;
        countMap.put(player, newCount);

        if (newCount >= 2 && !redCarded.contains(player)) {
            // Automatically issue red card
            RedCardEvent redCard = new RedCardEvent(time, player, true);
            redCard.apply(match);
            match.applyEvent(redCard);  // Log it too
        }
    }
}

