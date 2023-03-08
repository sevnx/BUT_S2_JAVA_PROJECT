import game.Game;

/**
 * Main class for the game.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class Main {
    /**
     * Main method
     * @param args Arguments given to the program.
     */
    public static void main(String[] args) {
        Game game = new Game(args);
        game.play();
    }
}
