import java.util.*;

public class DeckOfCards {
    private ArrayList<Card> deck;
    private final Random randomGenerator;

    public DeckOfCards() {
        deck = new ArrayList<>();
        generateAllPossibleCards();
        randomGenerator = new Random();
    }

    private void generateAllPossibleCards(){
        CardGenerator cardGenerator = new CardGenerator();
        deck=cardGenerator.getGeneratedCards();
    }

    // Method to check if the deck is empty
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    // Method to draw a card randomly from the deck, removing it from the deck
    public Card pickRandomCard() {
        Card pickedCard=deck.get(randomGenerator.nextInt(deck.size()));
        removeCardByIndex(deck.indexOf(pickedCard));
        return pickedCard;
    }

    private void removeCardByIndex(int index){
        deck.remove(index);
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