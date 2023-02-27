/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Animal.java
 * Enumeration of animals
 */

public enum Animal {
    LION, ELEPHANT, BEAR;
    public static final int NUMBER_OF_ANIMALS = Animal.values().length;
    @Override
    public String toString() {
        switch (this) {
            case LION:
                return "LION";
            case ELEPHANT:
                return "ELEPHANT";
            case BEAR:
                return "OURS";
            default:
                return "Unknown";
        }
    }
    public static int getMaxAnimalLength(){
        int max=0;
        for (Animal animal:Animal.values())
            if (animal.toString().length()>max)
                max=animal.toString().length();
        return max;
    }
}
