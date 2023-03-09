package cards;

import podium.Animal;
import podium.Podium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class generating all possible cards.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class CardGenerator {
    /** List of all possible combinations of animals with a single podium. */
    private final List<ArrayList<Animal>> basePermutations;
    /** ArrayList of all possible cards. */
    private final ArrayList<Card> generatedCards;

    /**
     * Constructor of the class that generates all possible cards thanks to the PermutationGenerator class.
     */
    public CardGenerator(){
        basePermutations = new PermutationGenerator<>(Arrays.asList(Animal.values())).getPermutations();
        generatedCards = new ArrayList<>();
        generateCards();
    }

    /**
     * Utility function that generates all possible cards from the basePermutations list.
     * It does so by creating 4 cards from each combination of animals,
     * because each basePermutation can be transformed into 4 cards by varying the number of animals on each podium.
     */
    private void generateCards(){
        ArrayList<Animal> blue=new ArrayList<>();
        ArrayList<Animal> red=new ArrayList<>();
        for (ArrayList<Animal> combination:basePermutations) {
            for (int i=0;i<=Animal.NUMBER_OF_ANIMALS;i++){
                blue.clear();
                red.clear();
                blue.addAll(combination.subList(0,i));
                red.addAll(combination.subList(i,Animal.NUMBER_OF_ANIMALS));
                generatedCards.add(new Card(new Podium(blue),new Podium(red)));
            }
        }
    }

    /**
     * Getter of the generatedCards attribute.
     * @return generatedCards
     */
    public List<Card> getGeneratedCards() {
        return generatedCards;
    }
}
