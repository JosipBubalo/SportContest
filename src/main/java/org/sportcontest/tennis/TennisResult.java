package org.sportcontest.tennis;

import org.sportcontest.core.MatchResult;
import org.sportcontest.core.Player;

import java.util.ArrayList;
import java.util.List;

public class TennisResult extends MatchResult {
    private List<Player> playerA;
    private List<Player> playerB;
    private List<Integer> setsA = new ArrayList<>();
    private List<Integer> setsB = new ArrayList<>();

    public TennisResult(List<Player> playerA, List<Player> playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void addSetResult(int gamesA, int gamesB) {
        setsA.add(gamesA);
        setsB.add(gamesB);
    }

    @Override
    public String getWinner() {
        int winA = 0, winB = 0;
        for (int i = 0; i < setsA.size(); i++) {
            if (setsA.get(i) > setsB.get(i)) winA++;
            else if (setsB.get(i) > setsA.get(i)) winB++;
        }
        if (winA > winB) return playerA.get(0).getName();
        if (winB > winA) return playerB.get(0).getName();
        return "Draw";
    }

    @Override
    public String getScore() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < setsA.size(); i++) {
            sb.append(setsA.get(i)).append("-").append(setsB.get(i));
            if (i < setsA.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public String getPhase() {
        int winA = 0, winB = 0;
        for (int i = 0; i < setsA.size(); i++) {
            if (setsA.get(i) > setsB.get(i)) winA++;
            else if (setsB.get(i) > setsA.get(i)) winB++;
        }

        boolean isGrandSlam = match != null &&
                match.getCompetition() != null &&
                match.getCompetition().getTypeComp().toLowerCase().contains("grand slam");

        int requiredSetsToWin = isGrandSlam ? 3 : 2;
        return (winA >= requiredSetsToWin || winB >= requiredSetsToWin) ? "Complete" : "In Progress";
    }
}