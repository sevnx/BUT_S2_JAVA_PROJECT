package tests;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.CardTest.java
 * @brief Unit tests for class "Card" with various methods.
 */

import cards.Card;
import podium.Animal;
import podium.Podium;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void testCommandKI(){
        ArrayList<Animal> blue = new ArrayList<>();
        ArrayList<Animal> red = new ArrayList<>();
        red.add(Animal.BEAR);
        blue.add(Animal.ELEPHANT);
        red.add(Animal.LION);
        Card card = new Card(new Podium(blue), new Podium(blue));
        card.executeCommand("KI");
        assert(card.getBlue().isEmpty());
        assertEquals(Animal.ELEPHANT, card.getRed().getTop());
    }

    @Test
    public void testEqualWithSelf(){
        ArrayList<Animal> blue = new ArrayList<>();
        ArrayList<Animal> red = new ArrayList<>();
        red.add(Animal.BEAR);
        blue.add(Animal.ELEPHANT);
        red.add(Animal.LION);
        Card card1 = new Card(new Podium(red), new Podium(blue));
        assertEquals(card1, card1);
    }

    @Test
    public void testEqualsWithCopy() {
        ArrayList<Animal> red = new ArrayList<>();
        ArrayList<Animal> blue = new ArrayList<>();
        red.add(Animal.BEAR);
        blue.add(Animal.ELEPHANT);
        red.add(Animal.LION);
        Card card1 = new Card(new Podium(red), new Podium(blue));
        Card card2 = new Card(card1);
        assertEquals(card1, card2);
    }

    @Test
    public void testNotEquals() {
        ArrayList<Animal> red = new ArrayList<>();
        ArrayList<Animal> blue = new ArrayList<>();
        red.add(Animal.BEAR);
        blue.add(Animal.ELEPHANT);
        red.add(Animal.LION);
        Card card1 = new Card(new Podium(red), new Podium(blue));
        Card card2 = new Card(new Podium(blue), new Podium(red));
        assertNotEquals(card1, card2);
    }

    @Test
    public void testEqualsWithCommands(){
        ArrayList<Animal> blue1 = new ArrayList<>();
        ArrayList<Animal> red1 = new ArrayList<>();
        ArrayList<Animal> blue2 = new ArrayList<>();
        ArrayList<Animal> red2 = new ArrayList<>();
        blue1.add(Animal.BEAR);
        blue1.add(Animal.ELEPHANT);
        blue1.add(Animal.LION);
        blue2.add(Animal.BEAR);
        blue2.add(Animal.ELEPHANT);
        red2.add(Animal.LION);
        assertNotEquals(blue1, blue2);
        Card card1 = new Card(new Podium(blue1), new Podium(red1));
        Card card2 = new Card(new Podium(blue2), new Podium(red2));
        card2.executeCommand("LO");
        assertEquals(card1.getBlue(), card2.getBlue());
        assertEquals(card1.getRed(), card2.getRed());
    }
}
