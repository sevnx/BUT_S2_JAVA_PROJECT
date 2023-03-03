package card;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.AnimalPermutationGenerator.java
 * @brief Class generating all possible permutations of animals.
 */
public class PermutationGenerator<E> {
    /** List of all possible permutations. */
    private final ArrayList<ArrayList<E>> permutations;
    /** List of all possible values, that we generate permutations of */
    private final ArrayList<E> values;

    /**
     * Constructor of the class that generates all possible permutations of a given list of values.
     * @param values List of values we want to generate permutations of.
     */
    public PermutationGenerator(List<E> values){
        permutations = new ArrayList<>();
        this.values=new ArrayList<>(values);
        permutations(0); // Call of recursive function that generates all possible permutations.
    }

    /**
     * Recursive function that generates all possible permutations of a given list of values.
     * @param currentIndex Index of the current value we are swapping.
     */
    private void permutations(int currentIndex){
        if (currentIndex==values.size()-1)
            permutations.add(new ArrayList<>(values));
        for (int i = currentIndex; i < values.size(); i++) {
            swap(values,currentIndex,i);
            permutations(currentIndex+1);
            swap(values,currentIndex,i);
        }
    }

    /**
     * Getter of the permutations attribute.
     * @return permutations
     */
    public List<ArrayList<E>> getPermutations() {
        return permutations;
    }
}
