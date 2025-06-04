package org.sportcontest.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Competition {
    private List<Match> matches;
    private String name;
    private String typeComp;
    private List<Participant> participants;
    private Date startDate;
    private Date endDate;

    public Competition(String name, String typeComp, Date startDate, Date endDate) {
        this.name = name;
        this.typeComp = typeComp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.matches = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Participant p) {
        participants.add(p);
    }

    public void removeParticipant(Participant p) {
        participants.remove(p);
    }

    public void scheduleMatch(Match match) {
        matches.add(match);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public String getName() {
        return name;
    }

    public String getTypeComp() {
        return typeComp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
