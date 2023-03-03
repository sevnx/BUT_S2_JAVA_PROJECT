package tests;

import game.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for class "Player" with various methods.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class PlayerTest {
    /**
     * Tests the constructor of the class.
     */
    @Test
    public void testPlayerConstructor() {
        Player p = new Player("test");
        assertEquals("test", p.getNickname());
        assertEquals(0, p.getScore());
        assertFalse(p.isCurrentTurnLost());
    }

    /**
     * Tests the loseTurn method.
     */
    @Test
    public void testLoseTurn() {
        Player p = new Player("test");
        p.loseTurn();
        assertTrue(p.isCurrentTurnLost());
    }

    /**
     * Tests the resetCurrentTurnLost method after a loseTurn call.
     */
    @Test
    public void testLoseTurnAndResetCurrentTurnLost() {
        Player p = new Player("test");
        p.loseTurn();
        p.resetCurrentTurnLost();
        assertFalse(p.isCurrentTurnLost());
    }

    /**
     * Tests the incrementScore method.
     */
    @Test
    public void testIncrementScore() {
        Player p = new Player("test");
        p.incrementScore();
        assertEquals(1, p.getScore());
    }

    /**
     * Tests the incrementScore, in a case where the player has lost the current turn.
     * @throws AssertionError because the player has lost the current turn.
     */
    @Test(expected = AssertionError.class)
    public void testIncrementScoreAssertion_CannotIncrementIfTurnLost() {
        Player p = new Player("test");
        p.loseTurn();
        p.incrementScore();
    }

    /**
     * Tests the incrementScore method with multiple calls.
     */
    @Test
    public void testMultipleIncrementScore() {
        Player p = new Player("test");
        p.incrementScore();
        p.incrementScore();
        p.incrementScore();
        assertEquals(3, p.getScore());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testMultipleIncrementScoreWithStringRepresentation() {
        Player p = new Player("test");
        p.incrementScore();
        p.incrementScore();
        p.incrementScore();
        assertEquals("test, Score : 3", p.toString());
    }
}
