package podium; /**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Podium.Podium.java
 * Class representing a podium
 */

import java.util.ArrayList;
import java.util.Objects;

public class Podium {

    private final ArrayList<Animal> animals; // The pile of animals
    public static int TOP_ROW = 2;
    public static int MIDDLE_ROW = 1;
    public static int BOTTOM_ROW = 0;

    public Podium(){
        this.animals = new ArrayList<>();
    }

    public Podium(ArrayList<Animal> animals){
        this.animals = new ArrayList<>(animals);
    }

    public Podium(Podium other){
        this(other.animals);
    }

    public void addAtTop(Animal animal) {
        animals.add(animal);
    }

    public void removeAtTop() {
        animals.remove(animals.size()-1);
    }

    public void removeAtBottom() {
        animals.remove(0);
    }

    public Animal getTop() {
        return animals.get(animals.size()-1);
    }

    public Animal getBottom() {
        return animals.get(0);
    }

    public String getAnimalStringByIndex(int index){
        if (index<0 || index>=animals.size())
            return "\t";
        return animals.get(index).toString();
    }

    public boolean isEmpty(){
        return animals.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podium podium = (Podium) o;
        return animals.equals(podium.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animals);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Animal animal : animals)
            sb.append(animal.toString());
        return sb.toString();
    }
}