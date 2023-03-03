package cards;

import java.util.*;

/**
 * Class representing a deck of cards.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class DeckOfCards {
    /** ArrayList representing the deck of cards */
    private ArrayList<Card> deck;
    /** Random generator used to pick a random cards from the deck */
    private final Random randomGenerator;

    /**
     * Constructor of the class that generates all possible cards thanks to the CardGenerator class.
     */
    public DeckOfCards() {
        deck = new ArrayList<>();
        generateAllPossibleCards();
        randomGenerator = new Random();
    }

    /**
     * Utility function that generates all possible thanks to the CardGenerator class.
     */
    private void generateAllPossibleCards(){
        CardGenerator cardGenerator = new CardGenerator();
        deck=(ArrayList<Card>)cardGenerator.getGeneratedCards();
    }

    /**
     * Checks if the deck is empty.
     * @return true if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * Picks a random cards from the deck (removes it).
     * @return the picked cards.
     */
    public Card pickRandomCard() {
        Card pickedCard=deck.get(randomGenerator.nextInt(deck.size()));
        removeCardByIndex(deck.indexOf(pickedCard));
        return pickedCard;
    }

    /**
     * Removes a cards from the deck by its index.
     * @param index index of the cards to remove.
     */
    private void removeCardByIndex(int index){
        deck.remove(index);
    }
}
