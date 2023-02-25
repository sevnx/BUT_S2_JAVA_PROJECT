import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.swap;

public class AnimalPermutationGenerator {

    private ArrayList<ArrayList<Animal>> permutations;
    private ArrayList<Animal> values;

    public AnimalPermutationGenerator(){
        permutations = new ArrayList<>();
        values=new ArrayList<>(Arrays.asList(Animal.values()));
        generatePermutations();
    }


    private void generatePermutations(){
        permutations(0);
    }

    private void permutations(int currentIndex){
        if (currentIndex==values.size()-1)
            permutations.add(new ArrayList<>(values));
        for (int i = currentIndex; i < values.size(); i++) {
            swap(values,currentIndex,i);
            permutations(currentIndex+1);
            swap(values,currentIndex,i);
        }
    }

    public ArrayList<ArrayList<Animal>> getPermutations() {
        return permutations;
    }

}
