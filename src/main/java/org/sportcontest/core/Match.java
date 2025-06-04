package org.sportcontest.core;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private List<Participant> participants;
    private MatchResult result;
    private String sport;
    private Competition competition;
    private List<Event> events;

    public Match(String sport, Competition competition, List<Participant> participants) {
        this.sport = sport;
        this.competition = competition;
        this.participants = participants;
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public List<Event> getEvents() {
        return events;
    }

    public MatchResult getResult() {
        return result;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String getSport() {
        return sport;
    }

    public Competition getCompetition() {
        return competition;
    }
}