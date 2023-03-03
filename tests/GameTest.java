package tests;

import game.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for class "Game" with various methods.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class GameTest {

    /** Tests the constructor of the class. */
    @Test
    public void testConstructor(){
        String[] args = new String[]{"bob","test"};
        Game game = new Game(args);
        assertNull(game.getGoalSituation());
        assertNotNull(game.getStartingSituation());
        assertNotEquals(game.getStartingSituation(), game.getGoalSituation());
    }

    /** Tests the hasDoubleNicknames method with good nicknames. */
    @Test
    public void testDoubleNicknames(){
        String[] args = new String[]{"bob","test"};
        assertFalse(Game.hasDoubleNicknames(args));
    }

    /** Tests the hasDoubleNicknames method with incorrect nicknames. */
    @Test
    public void testDoubleNicknames2(){
        String[] args = new String[]{"bob","bob"};
        assertTrue(Game.hasDoubleNicknames(args));
    }

    /** Tests the player function tests. */
    @Test
    public void testPlayersFunctions(){
        String[] args = new String[]{"bob","test"};
        Game game = new Game(args);
        assertTrue(game.canPlayerPlay("bob"));
        assertTrue(game.canPlayerPlay("test"));
        assertTrue(game.doesPlayerNotExist("test2"));
        assertFalse(game.hasAllButOnePlayerLostCurrentTurn());
    }

    /** Test the secret string function. */
    @Test
    public void testSecretString(){
        String test="test";
        String secret="%^&*</=#@";
        assertTrue(Game.isSecretString(secret));
        assertFalse(Game.isSecretString(test));
    }
}
