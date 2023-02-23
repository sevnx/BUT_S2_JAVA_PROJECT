import java.util.*;

public class DeckOfCards {
    private Deque<Card> deck;

    // Builder who creates the deck of Cards according to the possible starting and ending situations
    public DeckOfCards() {
        deck = new ArrayDeque<Card>();
        for (Animal a : Animal.values()) {
            for (Direction d : Direction.values()) {
                for (Situation s : Situation.values()) {
                    Card c = new Card(a, d, s);
                    deck.add(c);
                }
            }
        }
        Collections.shuffle((List)deck);
    }

    // Method to check if the deck is empty
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    // Method to draw a card randomly from the deck
    public Card pickRandomCard() {
        return deck.pollFirst();
    }
}