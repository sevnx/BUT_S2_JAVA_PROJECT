package tests;

import games.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.GameTest.java
 * @brief Unit tests for class "Game" with various methods.
 */
public class GameTest {

    @Test
    public void testConstructor(){
        String[] args = new String[]{"bob","test"};
        Game game = new Game(args);
        assertNull(game.getGoalSituation());
        assertNotNull(game.getStartingSituation());
        assertNotEquals(game.getStartingSituation(), game.getGoalSituation());
    }
    @Test
    public void testDoubleNicknames(){
        String[] args = new String[]{"bob","test"};
        assertFalse(Game.hasDoubleNicknames(args));
    }

    @Test
    public void testDoubleNicknames2(){
        String[] args = new String[]{"bob","bob"};
        assertTrue(Game.hasDoubleNicknames(args));
    }

    @Test
    public void testPlayersFunctions(){
        String[] args = new String[]{"bob","test"};
        Game game = new Game(args);
        assertTrue(game.canPlayerPlay("bob"));
        assertTrue(game.canPlayerPlay("test"));
        assertTrue(game.doesPlayerNotExist("test2"));
        assertFalse(game.hasAllButOnePlayerLostCurrentTurn());
    }

    @Test
    public void testSecretString(){
        String test="test";
        String secret="%^&*</=#@";
        assertTrue(Game.isSecretString(secret));
        assertFalse(Game.isSecretString(test));
    }
}