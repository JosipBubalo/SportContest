import org.sportcontest.core.*;
import org.sportcontest.squash.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SquashMatchFlowTest {

    private Player playerA;
    private Player playerB;
    private Match match;
    private Competition competition;

    @BeforeEach
    public void setup() {
        playerA = new Player("Player1", 1);
        playerB = new Player("Player2", 2);

        competition = new Competition("World Tour", "Pro", new Date(), new Date());
        match = new Match("Squash", competition, List.of(playerA, playerB));
    }

    @Test
    public void testFullSquashMatchFlow() {
        match.applyEvent(new StartMatchEventSquash(new Date()));
        assertTrue(match.isStarted());
        assertNotNull(match.getResult());

        // Log some points (just for log purposes)
        match.applyEvent(new PointEventSquash(new Date(), playerA.getName()));
        match.applyEvent(new PointEventSquash(new Date(), playerB.getName()));

        // 3 games played, all won by player A
        match.applyEvent(new GameEventSquash(new Date(), 11, 7));
        match.applyEvent(new GameEventSquash(new Date(), 11, 9));
        match.applyEvent(new GameEventSquash(new Date(), 11, 6));

        match.applyEvent(new EndMatchEventSquash(new Date()));
        assertTrue(match.isEnded());

        SquashResult result = (SquashResult) match.getResult();
        assertEquals("Player1", result.getWinner());
        assertEquals("11-7, 11-9, 11-6", result.getScore());
        assertEquals("Complete", result.getPhase());
    }

    @Test
    public void testIncompleteSquashMatch() {
        match.applyEvent(new StartMatchEventSquash(new Date()));
        match.applyEvent(new GameEventSquash(new Date(), 11, 7)); // Player A leads 1–0

        SquashResult result = (SquashResult) match.getResult();
        assertEquals("Player1", result.getWinner());
        assertEquals("11-7", result.getScore());
        assertEquals("In Progress", result.getPhase());
    }

    @Test
    public void testPlayerBWinsMatch() {
        match.applyEvent(new StartMatchEventSquash(new Date()));

        match.applyEvent(new GameEventSquash(new Date(), 8, 11));
        match.applyEvent(new GameEventSquash(new Date(), 7, 11));
        match.applyEvent(new GameEventSquash(new Date(), 9, 11));

        match.applyEvent(new EndMatchEventSquash(new Date()));
        SquashResult result = (SquashResult) match.getResult();

        assertEquals("Player2", result.getWinner());
        assertEquals("8-11, 7-11, 9-11", result.getScore());
        assertEquals("Complete", result.getPhase());
    }
}
