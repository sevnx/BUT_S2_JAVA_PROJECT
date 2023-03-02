package game;

import cards.Card;
import org.junit.Test;
import podium.Animal;
import podium.Podium;

import static org.junit.Assert.*;

public class CombinationFinderTest {
    @Test
    public void testIsCommandPossible_AllCommandsImpossible(){
        Podium blue = new Podium();
        Podium red = new Podium();
        Card test = new Card(blue, red);
        Card test2 = new Card(red, blue);
        CombinationFinder finder = new CombinationFinder(test, test2);
        for (CombinationFinder.POSSIBLE_COMMANDS command : CombinationFinder.POSSIBLE_COMMANDS.values()) {
            assertFalse(finder.isCommandPossible(command,""));
        }
    }

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
}