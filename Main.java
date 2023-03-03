import games.Game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Main.java
 * @brief Main class for the games.
 */

public class Main {
    public static void main(String[] args) {
        Game game = new Game(args);
        game.play();
    }
}
