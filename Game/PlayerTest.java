package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.PlayerTest.java
 * @brief Test class for Game.Player.java.
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

        @Test
        public void testPlayerConstructor() {
            Player p = new Player("test");
            assertEquals("test", p.getNickname());
            assertEquals(0, p.getScore());
            assertFalse(p.isCurrentTurnLost());
        }

        @Test
        public void testLoseTurn() {
            Player p = new Player("test");
            p.loseTurn();
            assertTrue(p.isCurrentTurnLost());
        }

        @Test
        public void testLoseTurnAndResetCurrentTurnLost() {
            Player p = new Player("test");
            p.loseTurn();
            p.resetCurrentTurnLost();
            assertFalse(p.isCurrentTurnLost());
        }

        @Test
        public void testIncrementScore() {
            Player p = new Player("test");
            p.incrementScore();
            assertEquals(1, p.getScore());
        }

        @Test(expected = AssertionError.class)
        public void testIncrementScoreAssertion_CannotIncrementIfTurnLost() {
            Player p = new Player("test");
            p.loseTurn();
            p.incrementScore();
        }

        @Test
        public void testMultipleIncrementScore() {
            Player p = new Player("test");
            p.incrementScore();
            p.incrementScore();
            p.incrementScore();
            assertEquals(3, p.getScore());
        }

        @Test
        public void testMultipleIncrementScoreWithStringRepresentation() {
            Player p = new Player("test");
            p.incrementScore();
            p.incrementScore();
            p.incrementScore();
            assertEquals("Joueur : test, Score : 3", p.toString());
        }
}
