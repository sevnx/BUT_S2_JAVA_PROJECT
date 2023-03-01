package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.CombinationInputState.java
 * Class enumerates possible combination input states.
 */

public enum CombinationInputState {
    NON_EXISTENT_PLAYER, INVALID_INPUT, CANNOT_PLAY;

    public static void displayCombinationInputState(CombinationInputState state){
        switch (state){
            case NON_EXISTENT_PLAYER:
                System.err.println("Le joueur n'existe pas");
                break;
            case INVALID_INPUT:
                System.out.println("Mauvaise séquence");
                break;
            case CANNOT_PLAY:
                System.out.println("Le joueur n'avait pas le droit de jouer");
                break;
        }
    }
}
