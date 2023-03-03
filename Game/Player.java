package game;

/**
 * Class representing a player in the game.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class Player {
    /** Nickname of the player. */
    private final String nickname;
    /** Score of the player. */
    private int score;
    /** Used to know if the player has lost the current turn. */
    private boolean currentTurnLost;

    /**
     * Creates a new player with a given nickname.
     * @param nickname the nickname of the player.
     */
    public Player(String nickname){
        this.nickname = nickname;
        score = 0;
        currentTurnLost = false;
    }

    /**
     * Checks if the player has lost the current turn.
     * @return true if the player has lost the current turn, false otherwise.
     */
    public boolean isCurrentTurnLost(){
        return currentTurnLost;
    }

    /**
     * Resets the current turn lost status of the player.
     */
    public void resetCurrentTurnLost(){
        currentTurnLost = false;
    }

    /**
     * Sets the current turn lost status of the player to true.
     * Happens after player enters a wrong combination.
     */
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

    /**
     * String representation of the player, with its nickname and score.
     * @return String representation of the player.
     */
    @Override
    public String toString(){
        return nickname+", Score : "+score;
    }

    /**
     * Getter of the score attribute.
     * @return score
     */
    public int getScore(){
        return score;
    }

    /**
     * Getter of the nickname attribute.
     * @return nickname
     */
    public String getNickname(){
        return nickname;
    }
}
