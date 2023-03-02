package podium;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Podium.Animal.java
 * @brief Enumeration of animals.
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
                return "";
        }
    }
}
