package podium;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Podium.Animal.java
 * @brief Enumeration of animals.
 */

import color.Color;

public enum Animal {
    LION(Color.ANSI.YELLOW), ELEPHANT(Color.ANSI.GREY), BEAR(Color.ANSI.WHITE);
    public static final int NUMBER_OF_ANIMALS = Animal.values().length;
    private final Color.ANSI color;

    Animal(Color.ANSI color) {
        this.color = color;
    }

    public Color.ANSI getColor(){
        return color;
    }

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
