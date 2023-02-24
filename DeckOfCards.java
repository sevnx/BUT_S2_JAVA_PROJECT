import java.util.*;

public class DeckOfCards {
    private ArrayList<Card> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
        generateAllPossibleCards();
    }

    private void generateAllPossibleCards(){
        // TODO : Generate permutations
        // TODO : Generate cards from permutations
    }
    // Method to check if the deck is empty
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    // Method to draw a card randomly from the deck
    public Card pickRandomCard() {
        return deck.get(0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : deck) {
            sb.append(card.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}