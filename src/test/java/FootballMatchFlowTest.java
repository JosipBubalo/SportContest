import org.sportcontest.core.*;
import org.sportcontest.football.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FootballMatchFlowTest {

    private Team teamA;
    private Team teamB;
    private Match match;
    private Competition competition;

    @BeforeEach
    public void setup() {
        teamA = new Team("Team A");
        teamA.addTeamPlayer(new TeamPlayer("Alice", 7, "Striker"));
        teamA.addTeamPlayer(new TeamPlayer("Bob", 6, "Midfielder"));

        teamB = new Team("Team B");
        teamB.addTeamPlayer(new TeamPlayer("Charlie", 10, "Forward"));
        teamB.addTeamPlayer(new TeamPlayer("David", 5, "Defender"));

        competition = new Competition("League Test", "group", new Date(), new Date());
        match = new Match("Football", competition, List.of(teamA, teamB));
    }

    @Test
    public void testFullMatchFlowWithEvents() {
        // 1. Start match
        match.applyEvent(new StartMatchEventFootball(new Date()));
        assertTrue(match.isStarted());
        assertNotNull(match.getResult());

        // 2. Team A scores a goal
        match.applyEvent(new GoalEvent(new Date(), "Team A"));
        assertEquals("1 - 0", match.getResult().getScore());

        // 3. VAR disallows the goal
        match.applyEvent(new VarCheckEvent(new Date(), "Team A"));
        assertEquals("0 - 0", match.getResult().getScore());

        // 4. Yellow card to player #6 (Bob)
        match.applyEvent(new YellowCardEvent(new Date(), 6));
        assertEquals(1, match.getYellowCardCount().get("6"));

        // 5. Second yellow → automatic red card
        match.applyEvent(new YellowCardEvent(new Date(), 6));
        assertTrue(match.getRedCardedPlayers().contains("6"));
        assertEquals(1, teamA.getTeamPlayers().size());

        // 6. Substitution: replace player #7 with new player
        match.applyEvent(new SubstitutionEvent(new Date(), 7, "Eve", 14, "Winger"));
        assertTrue(teamA.getTeamPlayers().stream().anyMatch(p -> p.getNumber() == 14));
        assertFalse(teamA.getTeamPlayers().stream().anyMatch(p -> p.getNumber() == 7));

        // 7. Halftime event — should not affect state but should log
        match.applyEvent(new HalftimeEvent(new Date()));
        assertTrue(match.getEvents().stream().anyMatch(e -> e instanceof HalftimeEvent));

        // 8. End the match
        match.applyEvent(new EndMatchEventFootball(new Date()));
        assertTrue(match.isEnded());

        // 9. Result check (should still be 0-0)
        FootballResult result = (FootballResult) match.getResult();
        assertEquals("0 - 0", result.getScore());
        assertEquals("Draw", result.getWinner());
        assertEquals("Full-time", result.getPhase());
    }

    @Test
    public void testDrawGoesToExtraTimeInKnockout() {
        Competition knockoutComp = new Competition("Cup Final", "knockout", new Date(), new Date());
        Match knockoutMatch = new Match("Football", knockoutComp, List.of(teamA, teamB));
        knockoutMatch.applyEvent(new StartMatchEventFootball(new Date()));

        // Both teams score 1 goal
        knockoutMatch.applyEvent(new GoalEvent(new Date(), "Team A"));
        knockoutMatch.applyEvent(new GoalEvent(new Date(), "Team B"));

        knockoutMatch.applyEvent(new EndMatchEventFootball(new Date()));
        FootballResult result = (FootballResult) knockoutMatch.getResult();

        assertEquals("1 - 1", result.getScore());
        assertEquals("Extra-time", result.getPhase());
    }

    @Test
    public void testRedCardOverturnedByVAR() {
        match.applyEvent(new StartMatchEventFootball(new Date()));
        match.applyEvent(new RedCardEvent(new Date(), 5));

        assertTrue(match.getRedCardedPlayers().contains("5"));
        teamB.getTeamPlayers().stream()
                .filter(tp -> tp.getNumber() == 5)
                .findFirst()
                .ifPresent(teamB::removeTeamPlayer);

        match.applyEvent(new VarCheckEvent(new Date(), "David", 5, "Defender"));

        // Should re-add the player
        assertTrue(teamB.getTeamPlayers().stream().anyMatch(tp -> tp.getNumber() == 5));
        assertFalse(match.getRedCardedPlayers().contains("5"));
    }
}
