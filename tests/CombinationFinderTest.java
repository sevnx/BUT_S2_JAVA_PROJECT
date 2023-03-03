package tests;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.CombinationFinderTest.java
 * @brief Class testing the CombinationFinder class.
 */

import cards.Card;
import game.CombinationFinder;
import org.junit.Test;
import podium.Animal;
import podium.Podium;

import static org.junit.Assert.*;

public class CombinationFinderTest {
    /**
     * Test the isCommandPossible method with all possible commands.
     * Creates empty podium, so all commands should be impossible and return false.
     */
    @Test
    public void testIsCommandPossible_AllCommandsImpossible(){
        Podium blue = new Podium();
        Podium red = new Podium();
        Card test = new Card(blue, red);
        Card test2 = new Card(red, blue);
        CombinationFinder finder = new CombinationFinder(test, test2);
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.KI,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.NI,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.LO,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.MA,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.SO,""));
    }

    /**
     * Test the isCommandPossible method with all possible commands on a situation where some commands
     * are possible and others aren't.
     */
    @Test
    public void testIsCommandPossible(){
        Podium blue = new Podium();
        Podium red = new Podium();
        blue.addAtTop(Animal.BEAR);
        blue.addAtTop(Animal.ELEPHANT);
        blue.addAtTop(Animal.LION);
        Card test = new Card(blue, red);
        Card test2 = new Card(red, blue);
        CombinationFinder finder = new CombinationFinder(test, test2);
        assertTrue(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.KI,""));
        assertTrue(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.NI,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.LO,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.MA,""));
        assertFalse(finder.isCommandPossible(CombinationFinder.POSSIBLE_COMMANDS.SO,""));
    }

    /**
     * Test the findCombination method with an easy combination to find for a situation.
     */
    @Test
    public void testFindCombination(){
        Podium blue = new Podium();
        Podium red = new Podium();
        Podium blue2 = new Podium();
        blue.addAtTop(Animal.BEAR);
        blue.addAtTop(Animal.ELEPHANT);
        blue.addAtTop(Animal.LION);
        blue2.addAtTop(Animal.LION);
        blue2.addAtTop(Animal.BEAR);
        blue2.addAtTop(Animal.ELEPHANT);
        Card test = new Card(blue, red);
        Card test2 = new Card(blue2, red);
        CombinationFinder finder = new CombinationFinder(test, test2);
        assertEquals(finder.findCombination(),"NINI");
    }

    /**
     * Test the applyAllCommands method with same combination as the previous test.
     */
    @Test
    public void testApplyAllCommands(){
        Podium blue = new Podium();
        Podium blue2 = new Podium();
        Podium red = new Podium();
        blue.addAtTop(Animal.BEAR);
        blue.addAtTop(Animal.ELEPHANT);
        blue.addAtTop(Animal.LION);
        blue2.addAtTop(Animal.LION);
        blue2.addAtTop(Animal.BEAR);
        blue2.addAtTop(Animal.ELEPHANT);
        Card test = new Card(blue, red);
        Card test2 = new Card(blue2, red);
        CombinationFinder finder = new CombinationFinder(test, test2);
        assertEquals(finder.applyAllCommands("NINI"),test2);
    }
}
