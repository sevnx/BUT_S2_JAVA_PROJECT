package card;

import java.util.*;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.DeckOfCards.java
 * @brief Class representing a deck of card.
 */
public class DeckOfCards {
    /** ArrayList representing the deck of card */
    private ArrayList<Card> deck;
    /** Random generator used to pick a random card from the deck */
    private final Random randomGenerator;

    /**
     * Constructor of the class that generates all possible card thanks to the CardGenerator class.
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
     * Picks a random card from the deck (removes it).
     * @return the picked card.
     */
    public Card pickRandomCard() {
        Card pickedCard=deck.get(randomGenerator.nextInt(deck.size()));
        removeCardByIndex(deck.indexOf(pickedCard));
        return pickedCard;
    }

    /**
     * Removes a card from the deck by its index.
     * @param index index of the card to remove.
     */
    private void removeCardByIndex(int index){
        deck.remove(index);
    }
}
