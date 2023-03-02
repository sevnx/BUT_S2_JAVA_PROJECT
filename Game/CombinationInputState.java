package game;

import color.Color;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.CombinationInputState.java
 * @brief Class that enumerates possible combination input states which are meant to be displayed.
 */

public enum CombinationInputState {
    NON_EXISTENT_PLAYER, INVALID_INPUT, CANNOT_PLAY, CORRECT_INPUT;

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
