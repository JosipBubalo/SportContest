import org.sportcontest.core.*;
import org.sportcontest.tennis.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TennisMatchFlowTest {

    private Player playerA;
    private Player playerB;
    private Match match;
    private Competition competition;

    @BeforeEach
    public void setup() {
        playerA = new Player("Novak Djokovic", 1);
        playerB = new Player("Carlos Alcaraz", 2);

        competition = new Competition("Roland Garros", "Grand Slam", new Date(), new Date());
        match = new Match("Tennis", competition, List.of(playerA, playerB));
    }

    @Test
    public void testFullTennisMatchFlow() {
        // Start match
        match.applyEvent(new StartMatchEventTennis(new Date()));
        assertTrue(match.isStarted());
        assertNotNull(match.getResult());

        // Simulate points and games (not counted in result, just logged)
        match.applyEvent(new PointEventTennis(new Date(), playerA.getName()));
        match.applyEvent(new PointEventTennis(new Date(), playerB.getName()));
        match.applyEvent(new GameEventTennis(new Date(), playerA.getName()));
        match.applyEvent(new GameEventTennis(new Date(), playerA.getName()));

        // Log 3 full sets for player A (grand slam = first to 3)
        match.applyEvent(new SetEvent(new Date(), 6, 4));
        match.applyEvent(new SetEvent(new Date(), 7, 5));
        match.applyEvent(new SetEvent(new Date(), 6, 3));

        // End match
        match.applyEvent(new EndMatchEventTennis(new Date()));
        assertTrue(match.isEnded());

        // Check final result
        TennisResult result = (TennisResult) match.getResult();
        assertEquals("Novak Djokovic", result.getWinner());
        assertEquals("6-4, 7-5, 6-3", result.getScore());
        assertEquals("Complete", result.getPhase());
    }

    @Test
    public void testTwoSetMatchInNormalCompetition() {
        Competition smallerComp = new Competition("ATP 250", "ATP", new Date(), new Date());
        Match shortMatch = new Match("Tennis", smallerComp, List.of(playerA, playerB));

        shortMatch.applyEvent(new StartMatchEventTennis(new Date()));
        shortMatch.applyEvent(new SetEvent(new Date(), 6, 2));
        shortMatch.applyEvent(new SetEvent(new Date(), 6, 3));
        shortMatch.applyEvent(new EndMatchEventTennis(new Date()));

        TennisResult result = (TennisResult) shortMatch.getResult();
        assertEquals("Novak Djokovic", result.getWinner());
        assertEquals("6-2, 6-3", result.getScore());
        assertEquals("Complete", result.getPhase());
    }

    @Test
    public void testMatchStillInProgress() {
        match.applyEvent(new StartMatchEventTennis(new Date()));
        match.applyEvent(new SetEvent(new Date(), 7, 6));
        // Do not finish match

        TennisResult result = (TennisResult) match.getResult();
        assertEquals("Novak Djokovic", result.getWinner());
        assertEquals("7-6", result.getScore());
        assertEquals("In Progress", result.getPhase());
    }
}
