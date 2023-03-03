package tests;

import org.junit.Test;
import podium.Animal;
import podium.Podium;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Unit tests for class "Podium" with various methods.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class PodiumTest {
    /**
     * Tests adding an animal at the top of the podium.
     */
    @Test
    public void testAddAtTop(){
        Podium podium = new Podium();
        podium.addAtTop(Animal.BEAR);
        assertEquals(Animal.BEAR, podium.getTop());
    }

    /**
     * Tests removing an animal at the top of the podium.
     */
    @Test
    public void testRemoveAtTop(){
        Podium podium = new Podium();
        podium.addAtTop(Animal.BEAR);
        podium.removeAtTop();
        assertTrue(podium.isEmpty());
    }

    /** Test removing an animal at the bottom of the podium. */
    @Test
    public void testRemoveAtBottom(){
        Podium podium = new Podium();
        podium.addAtTop(Animal.BEAR);
        podium.removeAtBottom();
        assertTrue(podium.isEmpty());
    }

    /** Tests the equals method with the same podium. */
    @Test
    public void testEqualsWithSelf(){
        Podium podium = new Podium(new ArrayList<>(Arrays.asList(Animal.values())));
        assertEquals(podium, podium);
    }

    /** Tests the equals method with a copy of a podium. */
    @Test
    public void testEqualsWithCopy(){
        Podium podium = new Podium(new ArrayList<>(Arrays.asList(Animal.values())));
        Podium podium2 = new Podium(podium);
        assertEquals(podium, podium2);
    }

    /** Tests the equals method with different podiums. */
    @Test
    public void testNotEquals(){
        Podium podium = new Podium(new ArrayList<>(Arrays.asList(Animal.values())));
        Podium podium2 = new Podium(new ArrayList<>());
        podium2.addAtTop(Animal.BEAR);
        assertNotEquals(podium, podium2);
    }

    /** Tests equals methods with moving animals. */
    @Test
    public void testEqualsWithManualAddAndMultipleAnimals(){
        Podium podium = new Podium();
        Podium podium2 = new Podium();
        podium.addAtTop(Animal.BEAR);
        podium.addAtTop(Animal.ELEPHANT);
        podium.addAtTop(Animal.LION);
        podium2.addAtTop(Animal.BEAR);
        podium2.addAtTop(Animal.ELEPHANT);
        podium.removeAtTop();
        assertEquals(podium, podium2);
    }
}
