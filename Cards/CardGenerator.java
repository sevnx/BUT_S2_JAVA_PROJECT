package cards; /**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.CardGenerator.java
 * Class generating all possible cards
 */

import podium.Animal;
import podium.Podium;

import java.util.ArrayList;

public class CardGenerator {
    private final ArrayList<ArrayList<Animal>> basePermutations;
    private final ArrayList<Card> generatedCards;

    public CardGenerator(){
        basePermutations = new AnimalPermutationGenerator().getPermutations();
        generatedCards = new ArrayList<>();
        generateCards();
    }

    private void generateCards(){
        ArrayList<Animal> blue=new ArrayList<>();
        ArrayList<Animal> red=new ArrayList<>();
        for (ArrayList<Animal> combination:basePermutations) {
            for (int i=0;i<=Animal.NUMBER_OF_ANIMALS;i++){
                blue.clear();
                red.clear();
                blue.addAll(combination.subList(0,i));
                red.addAll(combination.subList(i,Animal.NUMBER_OF_ANIMALS));
                generatedCards.add(new Card(new Podium(red),new Podium(blue)));
            }
        }
    }

    public ArrayList<Card> getGeneratedCards() {
        return generatedCards;
    }
}
