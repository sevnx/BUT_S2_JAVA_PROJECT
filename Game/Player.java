package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Player.java
 * @brief Class representing a player in the game.
 */

public class Player {
    private final String nickname;
    private int score;
    private boolean currentTurnLost;

    /**
     * Creates a new player with the given nickname.
     * @param nickname the nickname of the player.
     */
    public Player(String nickname){
        this.nickname = nickname;
        score = 0;
        currentTurnLost = false;
    }

    public boolean isCurrentTurnLost(){
        return currentTurnLost;
    }

    public void resetCurrentTurnLost(){
        currentTurnLost = false;
    }

    public void loseTurn(){
        currentTurnLost = true;
    }

    /**
     * Increments the score of the player by 1.
     * @throws AssertionError if the player has lost the current turn.
     */
    public void incrementScore(){
        assert(!currentTurnLost);
        score++;
    }

    @Override
    public String toString(){
        return "Joueur : "+nickname+", Score : "+score;
    }

    public int getScore(){
        return score;
    }

    public String getNickname(){
        return nickname;
    }
}
