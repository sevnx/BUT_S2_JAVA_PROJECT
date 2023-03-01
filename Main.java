import game.Game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Main.java
 * Main class for the game
 */

public class Main {
    public static void main(String[] args) {
        Game game = new Game(args);
        game.play();
        System.out.println(game);
    }
}
