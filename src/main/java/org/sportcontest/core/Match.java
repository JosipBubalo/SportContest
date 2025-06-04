package org.sportcontest.core;

import java.util.*;

public class Match {
    private List<Participant> participants;
    private MatchResult result;
    private String sport;
    private Competition competition;
    private List<Event> events;
    private boolean isStarted = false;
    private boolean isEnded = false;
    private Map<String, Integer> yellowCardCount = new HashMap<>();
    private Set<String> redCardedPlayers = new HashSet<>();


    public Match(String sport, Competition competition, List<Participant> participants) {
        this.sport = sport;
        this.competition = competition;
        this.participants = participants;
        this.events = new ArrayList<>();
    }

    public void applyEvent(Event event) {
        event.apply(this);
        events.add(event);
    }

    public void setResult(MatchResult result) {
        this.result = result;
        result.setMatch(this);
    }

    public MatchResult getResult() { return result; }

    public void startMatch() { isStarted = true; }

    public void endMatch() { isEnded = true; }

    public boolean isStarted() { return isStarted; }

    public boolean isEnded() { return isEnded; }

    public List<Participant> getParticipants() { return participants; }

    public List<Event> getEvents() { return events; }

    public String getSport() { return sport; }

    public Competition getCompetition() { return competition; }

    public Map<String, Integer> getYellowCardCount() {
        return yellowCardCount;
    }

    public Set<String> getRedCardedPlayers() {
        return redCardedPlayers;
    }
}