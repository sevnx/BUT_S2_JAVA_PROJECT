package podiums;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Podium.Podium.java
 * @brief Class representing a podiums.
 */
public class Podium {
    /** List of animals on the podiums, from bottom to top. */
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
     * @param animals List of animals on the podiums
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
     * Gets the size of the podiums
     * @return Size of the podiums
     */
    public int getSize(){
        return animals.size();
    }

    /**
     * Adds an animal at the top of the podiums
     * @param animal Animal to add
     */
    public void addAtTop(Animal animal) {
        animals.add(animal);
    }

    /**
     * Adds an animal at the top of the podiums
     */
    public void removeAtTop() {
        animals.remove(animals.size()-1);
    }

    /**
     * Removes an animal at the bottom of the podiums
     */
    public void removeAtBottom() {
        animals.remove(0);
    }

    /**
     * Getter of the animal at the top of the podiums
     * @return Animal at the top of the podiums
     */
    public Animal getTop() {
        return animals.get(animals.size()-1);
    }

    /**
     * Getter of the animal at the bottom of the podiums
     * @return Animal at the bottom of the podiums
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
     * Checks if the podiums is empty
     * @return True if the podiums is empty, false otherwise
     */
    public boolean isEmpty(){
        return animals.isEmpty();
    }

    /**
     * Compares two podiums
     * @param o Podium to compare
     * @return True if the podiums are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podium podium = (Podium) o;
        return animals.equals(podium.animals);
    }

    /**
     * Hashcode of a podiums
     * @return Hashcode of a podiums
     */
    @Override
    public int hashCode() {
        return Objects.hash(animals);
    }
}
