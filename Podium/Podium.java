package podium;

/**
 * Class representing a podium.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Podium {
    /** List of animals on the podium, from bottom to top. */
    private final ArrayList<Animal> animals;
    /** Index of the top row. */
    public static final int TOP_ROW = 2;
    /** Index of the middle row. */
    public static final int MIDDLE_ROW = 1;
    /** Index of the bottom row. */
    public static final int BOTTOM_ROW = 0;

    /**
     * Empty constructor of Podium class
     */
    public Podium(){
        this.animals = new ArrayList<>();
    }

    /**
     * Constructor of Podium class
     * @param animals List of animals on the podium
     */
    public Podium(List<Animal> animals){
        this.animals = new ArrayList<>(animals);
    }

    /**
     * Copy constructor of Podium class
     * @param other Podium to copy
     */
    public Podium(Podium other){
        this(other.animals);
    }

    /**
     * Gets the size of the podium
     * @return Size of the podium
     */
    public int getSize(){
        return animals.size();
    }

    /**
     * Adds an animal at the top of the podium
     * @param animal Animal to add
     */
    public void addAtTop(Animal animal) {
        animals.add(animal);
    }

    /**
     * Adds an animal at the top of the podium
     */
    public void removeAtTop() {
        animals.remove(animals.size()-1);
    }

    /**
     * Removes an animal at the bottom of the podium
     */
    public void removeAtBottom() {
        animals.remove(0);
    }

    /**
     * Getter of the animal at the top of the podium
     * @return Animal at the top of the podium
     */
    public Animal getTop() {
        return animals.get(animals.size()-1);
    }

    /**
     * Getter of the animal at the bottom of the podium
     * @return Animal at the bottom of the podium
     */
    public Animal getBottom() {
        return animals.get(0);
    }

    /**
     * Getter of the animal at the given index
     * @param index Index of the animal
     * @return Animal at the given index
     */
    public Animal getAnimalByIndex(int index){
        if (index<0 || index>=animals.size())
            return null;
        return animals.get(index);
    }

    /**
     * Checks if the podium is empty
     * @return True if the podium is empty, false otherwise
     */
    public boolean isEmpty(){
        return animals.isEmpty();
    }

    /**
     * Compares two podium
     * @param o Podium to compare
     * @return True if the podium are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podium podium = (Podium) o;
        return animals.equals(podium.animals);
    }

    /**
     * Hashcode of a podium
     * @return Hashcode of a podium
     */
    @Override
    public int hashCode() {
        return Objects.hash(animals);
    }
}
