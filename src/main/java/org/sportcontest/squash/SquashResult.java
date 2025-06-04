package org.sportcontest.squash;

import org.sportcontest.core.MatchResult;
import org.sportcontest.core.Player;

import java.util.ArrayList;
import java.util.List;

public class SquashResult extends MatchResult {
    private List<Player> playerA;
    private List<Player> playerB;
    private List<Integer> gamesA = new ArrayList<>();
    private List<Integer> gamesB = new ArrayList<>();

    public SquashResult(List<Player> playerA, List<Player> playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void addGameResult(int pointsA, int pointsB) {
        gamesA.add(pointsA);
        gamesB.add(pointsB);
    }

    @Override
    public String getWinner() {
        int winA = 0, winB = 0;
        for (int i = 0; i < gamesA.size(); i++) {
            if (gamesA.get(i) > gamesB.get(i)) winA++;
            else if (gamesB.get(i) > gamesA.get(i)) winB++;
        }
        if (winA > winB) return playerA.get(0).getName();
        if (winB > winA) return playerB.get(0).getName();
        return "Draw";
    }

    @Override
    public String getScore() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gamesA.size(); i++) {
            sb.append(gamesA.get(i)).append("-").append(gamesB.get(i));
            if (i < gamesA.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public String getPhase() {
        int winA = 0, winB = 0;
        for (int i = 0; i < gamesA.size(); i++) {
            if (gamesA.get(i) > gamesB.get(i)) winA++;
            else if (gamesB.get(i) > gamesA.get(i)) winB++;
        }

        return (winA >= 3 || winB >= 3) ? "Complete" : "In Progress";
    }
}
