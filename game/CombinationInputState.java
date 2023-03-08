package game;

import color.Color;

/**
 * Class that enumerates possible combination input states which are meant to be displayed.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public enum CombinationInputState {
    /** Indicates that the player does not exist. */
    NON_EXISTENT_PLAYER,
    /** Indicates that the input was invalid. */
    INVALID_INPUT,
    /** Indicates that the player was not allowed to play. */
    CANNOT_PLAY,
    /** Indicates that the input was correct. */
    CORRECT_INPUT;

    /**
     * Utility function that displays the combination input state, and the associated message.
     * @param state Combination input state to display.
     */
    public static void displayCombinationInputState(CombinationInputState state){
        switch (state){
            case NON_EXISTENT_PLAYER:
                System.err.println(Color.coloredString(Color.ANSI.RED,
                        "Le joueur n'existe pas"));
                break;
            case INVALID_INPUT:
                System.out.println(Color.coloredString(Color.ANSI.RED,
                        "Mauvaise séquence"));
                break;
            case CANNOT_PLAY:
                System.out.println(Color.coloredString(Color.ANSI.RED,
                        "Le joueur n'avait pas le droit de jouer"));
                break;
            case CORRECT_INPUT:
                System.out.println(Color.coloredString(Color.ANSI.GREEN,
                        "Félicitations! Séquence correcte"));
                break;
        }
    }
}
